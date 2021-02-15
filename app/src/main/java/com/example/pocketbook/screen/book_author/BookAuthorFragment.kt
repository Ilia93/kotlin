package com.example.pocketbook.screen.book_author

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
import com.example.pocketbook.data.network.model.BookAuthorModel
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.BookAuthorFragmentBinding
import com.example.pocketbook.screen.book.SelectedBookFragment
import com.example.pocketbook.screen.book.SelectedBookFragment.Companion.SELECTED_BOOK_AUTHOR_ARG
import com.example.pocketbook.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.my_books.MyBooksFragment
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookAuthorFragment : Fragment(), ItemListener<BookModel> {

    companion object {
        fun getInstance(): BookAuthorFragment {
            return BookAuthorFragment()
        }
    }

    private lateinit var binding: BookAuthorFragmentBinding
    private var authorNameFromArgument: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookAuthorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        authorNameFromArgument = arguments?.getString(SELECTED_BOOK_AUTHOR_ARG).toString()
        binding.bookAuthorName.text = authorNameFromArgument
        setClickListeners()
        loadAuthorData()
        getBookAuthorBooks()
        return view
    }

    override fun onResume() {
        super.onResume()
        authorNameFromArgument = arguments?.getString(SELECTED_BOOK_AUTHOR_ARG).toString()
        binding.bookAuthorName.text = authorNameFromArgument
    }

    private fun setClickListeners() {
        binding.bookAuthorToolbar.setNavigationOnClickListener(View.OnClickListener {
            changeFragment(MyBooksFragment.getInstance())
        })
    }

    private fun loadAuthorData() {
        NetworkClient.buildBookAuthorApi().getBookAuthor().enqueue(
            object : Callback<List<BookAuthorModel>> {
                override fun onResponse(
                    call: Call<List<BookAuthorModel>>,
                    response: Response<List<BookAuthorModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        getBookAuthor(response)
                    } else if (response.errorBody() != null) {
                        showToast(DATA_FAIL)
                    }
                }

                override fun onFailure(call: Call<List<BookAuthorModel>>, t: Throwable) {
                    showToast(LOAD_ERROR)
                }

            }
        )
    }

    private fun getBookAuthor(response: Response<List<BookAuthorModel>>) {
        val list = response.body()
        if (list != null) {
            for (i in list.indices) {
                if (list[i].bookAuthor == authorNameFromArgument) {
                    binding.bookAuthorBiography.text = list[i].authorBiography
                    activity?.let {
                        Glide.with(it)
                            .load(list[i].bookAuthorImageUrl)
                            .transform(
                                RoundedCornersTransformation(
                                    10,
                                    10,
                                    RoundedCornersTransformation.CornerType.ALL
                                )
                            )
                            .into(binding.bookAuthorImage)
                    }
                }
            }
        }
    }

    private fun getBookAuthorBooks() {
        NetworkClient.buildBookApiClient().getBookAuthor(authorNameFromArgument).enqueue(
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
        binding.bookAuthorAllBooksRecyclerView.layoutManager = linearLayoutManager
        val list = response.body()
        val authorBooks: MutableList<BookModel> = mutableListOf()
        if (list != null) {
            for (i in list.indices) {
                if (list[i].bookAuthor == authorNameFromArgument) {
                    authorBooks.add(list[i])
                }
            }
        }
        val booksCount = authorBooks.size
        val sb = StringBuilder()
        sb.append(booksCount).append(" ").append("книги")
        binding.bookAuthorBooksNumber.text = sb.toString()
        val adapter = authorBooks.let { RelatedBooksAdapter(context, it, this) }
        binding.bookAuthorAllBooksRecyclerView.adapter = adapter
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frame, fragment)?.commit()
    }


    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(model: BookModel) {
        changeFragment(
            SelectedBookFragment.getInstance(
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
                model.bookSeries
            )
        )
    }
}