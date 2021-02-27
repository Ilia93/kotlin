package com.example.pocketbook.presentation.screen.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.SelectedBookFragmentBinding
import com.example.pocketbook.presentation.screen.book_author.BookAuthorFragment
import com.example.pocketbook.presentation.screen.book_author.recycler_view.related_books.RelatedBooksAdapter
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.my_books.MyBooksFragment
import com.example.pocketbook.presentation.screen.subscribe.SubscribeFragment
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class SelectedBookFragment : Fragment(), ItemListener<BookModel> {

    companion object {

        fun getInstance(
            bookRating: Int,
            bookUrl: String,
            bookName: String,
            bookAuthor: String,
            bookDescription: String,
            bookAgeLimit: String,
            bookSubscribeType: String,
            bookIsFinished: Boolean,
            bookLanguage: String,
            bookStyle: String,
            bookSeries: String?,
            marksCount: String
        ): SelectedBookFragment {
            val fragmentArguments = Bundle()
            val fragment =
                SelectedBookFragment()
            fragmentArguments.putInt(
                SELECTED_BOOK_RATING_ARG,
                bookRating
            )
            fragmentArguments.putString(
                SELECTED_BOOK_URL_ARG,
                bookUrl
            )
            fragmentArguments.putString(
                SELECTED_BOOK_NAME_ARG,
                bookName
            )
            fragmentArguments.putString(
                SELECTED_BOOK_AUTHOR_ARG,
                bookAuthor
            )
            fragmentArguments.putString(
                SELECTED_BOOK_DESCRIPTION_ARG,
                bookDescription
            )
            fragmentArguments.putString(
                SELECTED_BOOK_AGE_LIMIT_ARG,
                bookAgeLimit
            )
            fragmentArguments.putString(
                SELECTED_BOOK_SUBSCRIBE_ARG,
                bookSubscribeType
            )
            fragmentArguments.putBoolean(
                SELECTED_BOOK_IS_FINISHED_ARG,
                bookIsFinished
            )
            fragmentArguments.putString(
                SELECTED_BOOK_LANGUAGE_ARG,
                bookLanguage
            )
            fragmentArguments.putString(
                SELECTED_BOOK_STYLE_ARG,
                bookStyle
            )
            fragmentArguments.putString(
                SELECTED_BOOK_SERIES_ARG,
                bookSeries
            )
            fragmentArguments.putString(SELECTED_BOOK_MARKS_COUNT, marksCount)
            fragment.arguments = fragmentArguments
            return fragment
        }

        const val SELECTED_BOOK_URL_ARG = "bookUrl"
        const val SELECTED_BOOK_NAME_ARG = "bookName"
        const val SELECTED_BOOK_AUTHOR_ARG = "bookAuthor"
        const val SELECTED_BOOK_RATING_ARG = "bookRating"
        const val SELECTED_BOOK_AGE_LIMIT_ARG = "bookAgeLimit"
        const val SELECTED_BOOK_SUBSCRIBE_ARG = "bookSubscribeType"
        const val SELECTED_BOOK_IS_FINISHED_ARG = "bookIsFinished"
        const val SELECTED_BOOK_DESCRIPTION_ARG = "bookDescription"
        const val SELECTED_BOOK_LANGUAGE_ARG = "bookLanguage"
        const val SELECTED_BOOK_STYLE_ARG = "bookStyle"
        const val SELECTED_BOOK_SERIES_ARG = "bookSeries"
        const val SELECTED_BOOK_MARKS_COUNT = "bookMarks"
    }

    lateinit var binding: SelectedBookFragmentBinding
    private var selectedBookSeries: String = ""
    private var selectedBookStyle: String = ""
    private var selectedBookName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectedBookFragmentBinding.inflate(inflater, container, false)
        setOnClickListeners()
        loadBookSeries()
        return binding.root
    }

    //TODO переделать метод, очень большой
    override fun onResume() {
        super.onResume()
        if (arguments?.getString(SELECTED_BOOK_URL_ARG) != null) {
            val bookUrl =
                arguments?.getString(SELECTED_BOOK_URL_ARG)
            val isFinishedFlag =
                arguments?.getBoolean(SELECTED_BOOK_IS_FINISHED_ARG)
            activity?.let {
                Glide.with(it)
                    .load(bookUrl)
                    .transform(RoundedCornersTransformation(10, 10))
                    .into(binding.selectedBookImage)
            }

            activity?.let {
                Glide.with(it)
                    .load(bookUrl)
                    .transform(BlurTransformation(10))
                    .into(binding.selectedBookToolbarBackground)
            }
            binding.selectedBookName.text =
                arguments?.getString(SELECTED_BOOK_NAME_ARG)
            binding.selectedBookAuthor.text =
                arguments?.getString(SELECTED_BOOK_AUTHOR_ARG)
            binding.selectedBookRatingMark.text =
                arguments?.getInt(SELECTED_BOOK_RATING_ARG)
                    .toString()
            val rating: Float? =
                arguments?.getInt(SELECTED_BOOK_RATING_ARG)
                    ?.toFloat()
            if (rating != null) {
                binding.selectedBookRating.rating = rating
            }
            setRating()
            binding.selectedBookAgeLimit.text =
                arguments?.getString(SELECTED_BOOK_AGE_LIMIT_ARG)
            binding.selectedBookSubscribeBtn.text =
                arguments?.getString(SELECTED_BOOK_SUBSCRIBE_ARG)
            if (isFinishedFlag == true) {
                binding.selectedBookStatus.text =
                    resources.getString(R.string.selectedBook_finished_read_status)
            } else if (isFinishedFlag == false) {
                binding.selectedBookStatus.text =
                    resources.getString(R.string.selectedBook_read_status)
            }
            binding.selectedBookAnnotation.text =
                arguments?.getString(SELECTED_BOOK_DESCRIPTION_ARG)
            binding.selectedBookStyleTag.text =
                arguments?.getString(SELECTED_BOOK_STYLE_ARG)
            binding.selectedBookStyleTag.visibility = View.VISIBLE
            binding.selectedBookLanguage.text =
                arguments?.getString(SELECTED_BOOK_LANGUAGE_ARG)
            if (arguments?.getString(SELECTED_BOOK_SERIES_ARG) != null) {
                binding.selectedBookSeriesTag.text =
                    arguments?.getString(SELECTED_BOOK_SERIES_ARG)
                binding.selectedBookSeriesTag.visibility = View.VISIBLE
                binding.appCompatTextView8.visibility = View.VISIBLE
                binding.selectedBookRelatedSeriesRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun setRating(){
        val builder = StringBuilder()
        val result = arguments?.getString(SELECTED_BOOK_MARKS_COUNT)
        builder.append(result).append("").append("читателей оценили")
        binding.selectedBookReadersNumber.text = builder.toString()
    }

    private fun setOnClickListeners() {
        binding.selectedBookSubscribeBtn.setOnClickListener {
            changeFragment(SubscribeFragment.getInstance())
        }

        binding.selectedBookToolbar.setNavigationOnClickListener {
            changeFragment(MyBooksFragment.getInstance())
        }
        binding.selectedBookAuthor.setOnClickListener {
            changeFragment(BookAuthorFragment.getInstance(binding.selectedBookAuthor.text.toString()))
        }
        binding.selectedBookAddPremiumCard.setOnClickListener {
            changeFragment(SubscribeFragment.getInstance())
        }
        binding.selectedBookDefaultRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            showToast(rating.toString())
        }

    }


    private fun loadBookSeries() {
        NetworkClient.buildBookApiClient()
            .getBookAuthor(
                arguments?.getString(SELECTED_BOOK_AUTHOR_ARG)
                    .toString()
            ).enqueue(
                object : Callback<List<BookModel>> {
                    override fun onResponse(
                        call: Call<List<BookModel>>,
                        response: Response<List<BookModel>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            setRecyclerViewAdapter(response)
                        }
                    }

                    override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                        showToast(LOAD_ERROR)
                    }

                }
            )
    }

    private fun setRecyclerViewAdapter(response: Response<List<BookModel>>) {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.selectedBookRelatedSeriesRecyclerView.layoutManager = linearLayoutManager
        val relatedBooksLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.selectedBookRelatedBookRecyclerView.layoutManager = relatedBooksLayoutManager
        val list = response.body()
        val relatedBooks: MutableList<BookModel> = mutableListOf()
        val bookSeries: MutableList<BookModel> = mutableListOf()
        selectedBookSeries =
            arguments?.getString(SELECTED_BOOK_SERIES_ARG)
                .toString()
        selectedBookStyle =
            arguments?.getString(SELECTED_BOOK_STYLE_ARG)
                .toString()
        selectedBookName =
            arguments?.getString(SELECTED_BOOK_NAME_ARG)
                .toString()
        if (list != null) {
            for (i in list.indices) {
                if (list[i].bookSeries == selectedBookSeries) {
                    bookSeries.add(list[i])
                }
            }
            for (i in list.indices) {
                if (list[i].bookStyle == selectedBookStyle) {
                    relatedBooks.add(list[i])
                }
            }
        }
        for (i in relatedBooks.indices) {
            if (relatedBooks[i].bookName == selectedBookName) {
                relatedBooks.removeAt(i)
                break
            }
        }
        val relatedBooksAdapter = RelatedBooksAdapter(context, relatedBooks, this)
        val adapter = RelatedBooksAdapter(context, bookSeries, this)
        binding.selectedBookRelatedSeriesRecyclerView.adapter = adapter
        binding.selectedBookRelatedBookRecyclerView.adapter = relatedBooksAdapter
    }


    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frame, fragment)?.commit()
    }

    override fun itemClicked(model: BookModel) {
        changeFragment(
            getInstance(
                model.bookRating,
                model.imageUrl,
                model.bookName,
                model.bookAuthor,
                model.bookAnnotation,
                model.ageLimit,
                model.typeOfBookSubscribe,
                model.isBookFinished,
                model.bookLanguage,
                model.bookStyle,
                model.bookSeries,
                model.marksCount
            )
        )
    }
}
