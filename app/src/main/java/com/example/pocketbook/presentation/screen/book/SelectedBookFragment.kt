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
import com.example.pocketbook.data.network.model.book_model.BookModel
import com.example.pocketbook.data.network.model.book_model.BookRating
import com.example.pocketbook.databinding.SelectedBookFragmentBinding
import com.example.pocketbook.presentation.App
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
            marksCount: Int,
            objectId: String,
            marksSum: Int,
            isRated: Boolean
        ): SelectedBookFragment {
            val fragment = SelectedBookFragment()
            val fragmentArguments = Bundle()
            fragmentArguments.putInt(SELECTED_BOOK_RATING_ARG, bookRating)
            fragmentArguments.putString(SELECTED_BOOK_URL_ARG, bookUrl)
            fragmentArguments.putString(SELECTED_BOOK_NAME_ARG, bookName)
            fragmentArguments.putString(SELECTED_BOOK_AUTHOR_ARG, bookAuthor)
            fragmentArguments.putString(SELECTED_BOOK_DESCRIPTION_ARG, bookDescription)
            fragmentArguments.putString(SELECTED_BOOK_AGE_LIMIT_ARG, bookAgeLimit)
            fragmentArguments.putString(SELECTED_BOOK_SUBSCRIBE_ARG, bookSubscribeType)
            fragmentArguments.putBoolean(SELECTED_BOOK_IS_FINISHED_ARG, bookIsFinished)
            fragmentArguments.putString(SELECTED_BOOK_LANGUAGE_ARG, bookLanguage)
            fragmentArguments.putString(SELECTED_BOOK_STYLE_ARG, bookStyle)
            fragmentArguments.putString(SELECTED_BOOK_SERIES_ARG, bookSeries)
            fragmentArguments.putInt(SELECTED_BOOK_MARKS_COUNT, marksCount)
            fragmentArguments.putString(SELECTED_BOOK_OBJECT_ID, objectId)
            fragmentArguments.putInt(SELECTED_BOOK_MARKS_SUM, marksSum)
            fragmentArguments.putBoolean(SELECTED_BOOK_IS_RATED, isRated)
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
        const val SELECTED_BOOK_IS_RATED = "bookIsRated"
        const val SELECTED_BOOK_MARKS_SUM = "bookMarksSum"
        const val SELECTED_BOOK_OBJECT_ID = "objectId"
    }

    lateinit var binding: SelectedBookFragmentBinding
    private var selectedBookSeries = ""
    private var selectedBookStyle = ""
    private var selectedBookName = ""
    private var bookUrl: String? = ""
    private var isFinishedFlag: Boolean? = false
    private var bookName: String? = ""
    private var bookAuthor: String? = ""
    private var bookMarkSum: Int = 0
    private var bookMarkCount: Int = 0
    private var isRated: Boolean = false
    private var bookRating = 0
    private var ageLimit = " "
    private var bookSubscribe = " "
    private var bookAnnotation = " "
    private var bookStyle = " "
    private var bookLanguage = " "
    private var bookSeries: String = " "

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

    override fun onResume() {
        super.onResume()
        initializeFields()
        loadImages(bookUrl)
        setBookFields()
        setReaders()
        setBookStatus()
        setVisibility()
    }

    private fun getArgument(argumentKey: String): Any? {
        return arguments?.get(argumentKey)
    }

    private fun setBookFields() {
        binding.selectedBookName.text = bookName
        binding.selectedBookAuthor.text = bookAuthor
        binding.selectedBookRatingMark.text = bookRating.toString()
        binding.selectedBookRating.rating = bookRating.toFloat()
        binding.selectedBookAgeLimit.text = ageLimit
        binding.selectedBookSubscribeBtn.text = bookSubscribe
        binding.selectedBookAnnotation.text = bookAnnotation
        binding.selectedBookStyleTag.text = bookStyle
        binding.selectedBookLanguage.text = bookLanguage
        binding.selectedBookSeriesTag.text = bookSeries
    }

    private fun initializeFields() {
        bookUrl = getArgument(SELECTED_BOOK_URL_ARG) as String?
        isFinishedFlag = getArgument(SELECTED_BOOK_IS_FINISHED_ARG) as Boolean?
        bookName = getArgument(SELECTED_BOOK_NAME_ARG) as String?
        bookAuthor = getArgument(SELECTED_BOOK_AUTHOR_ARG) as String?
        bookRating = getArgument(SELECTED_BOOK_RATING_ARG) as Int
        bookMarkSum = getArgument(SELECTED_BOOK_MARKS_SUM) as Int
        bookMarkCount = getArgument(SELECTED_BOOK_MARKS_COUNT) as Int
        ageLimit = getArgument(SELECTED_BOOK_AGE_LIMIT_ARG) as String
        bookSubscribe = getArgument(SELECTED_BOOK_SUBSCRIBE_ARG) as String
        isRated = getArgument(SELECTED_BOOK_IS_RATED) as Boolean
        bookAnnotation = getArgument(SELECTED_BOOK_DESCRIPTION_ARG) as String
        bookStyle = getArgument(SELECTED_BOOK_STYLE_ARG) as String
        bookLanguage = getArgument(SELECTED_BOOK_LANGUAGE_ARG) as String
        if (getArgument(SELECTED_BOOK_SERIES_ARG) != null){
            bookSeries = getArgument(SELECTED_BOOK_SERIES_ARG) as String
        }
    }

    private fun loadImages(bookUrl: String?) {
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
    }

    private fun setReaders() {
        val builder = StringBuilder()
        val result = arguments?.getInt(SELECTED_BOOK_MARKS_COUNT)
        builder.append(result).append(" ").append("читателей оценили")
        binding.selectedBookReadersNumber.text = builder.toString()
    }

    private fun setBookStatus() {
        if (isFinishedFlag == true) {
            binding.selectedBookStatus.text =
                resources.getString(R.string.selectedBook_finished_read_status)
        } else if (isFinishedFlag == false) {
            binding.selectedBookStatus.text =
                resources.getString(R.string.selectedBook_read_status)
        }
    }

    private fun setVisibility() {
        binding.selectedBookStyleTag.visibility = View.VISIBLE
        binding.selectedBookSeriesTag.visibility = View.VISIBLE
        binding.appCompatTextView8.visibility = View.VISIBLE
        binding.selectedBookRelatedSeriesRecyclerView.visibility = View.VISIBLE
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
            showToast("$rating")
            doRatingLogic(rating)
        }
    }

    private fun doRatingLogic(rating: Float) {
        val objectId: String = arguments?.getString(SELECTED_BOOK_OBJECT_ID).toString()
        isRated = true
        bookMarkCount++
        val bookMarkSumTemporary: Int = bookMarkSum + rating.toInt()
        val bookRatingTemporary: Int = bookMarkSumTemporary / bookMarkCount
        val model = BookRating(
            isRated,
            bookMarkCount,
            bookMarkSumTemporary,
            bookRatingTemporary,
            objectId
        )
        /* NetworkClient.buildBookApiClient().updateBookRating(
             objectId,
             model
         ).enqueue(object : Callback<BookRating>)*/
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
        binding.selectedBookRelatedSeriesRecyclerView.layoutManager =
            App.setLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL)
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
                model.marksCount,
                model.objectId,
                model.marksSum,
                model.isRated
            )
        )
    }
}
