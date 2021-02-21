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
import com.example.pocketbook.screen.book.SelectedBookFragment.Companion.SELECTED_BOOK_STYLE_ARG
import com.example.pocketbook.screen.book_author.recycler_view.related_authors.RelatedAuthorsAdapter
import com.example.pocketbook.screen.book_author.recycler_view.related_books.RelatedBooksAdapter
import com.example.pocketbook.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.screen.main.top.ImageListener
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.my_books.MyBooksFragment
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookAuthorFragment : Fragment(), ItemListener<BookModel>, ImageListener {

    companion object {
        const val BOOK_AUTHOR_ARG = "bookAuthor"

        fun getInstance(bookAuthorName: String): BookAuthorFragment {
            val arguments = Bundle()
            val fragment = BookAuthorFragment()
            arguments.putString(BOOK_AUTHOR_ARG, bookAuthorName)
            fragment.arguments = arguments
            return fragment
        }
    }

    private lateinit var binding: BookAuthorFragmentBinding
    private val FAILED_TO_LOAD_RELATED_AUTHORS = "Failed to load related authors"
    private var authorNameFromArgument: String = ""
    private var selectedAuthorBookStyle: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookAuthorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        getAuthorNameFromArguments()
        setClickListeners()
        loadAuthorData()
        getAuthorBooks()
        loadRelatedAuthors()
        return view
    }

    override fun onResume() {
        super.onResume()
        getAuthorNameFromArguments()
    }

    private fun getAuthorNameFromArguments() {
        authorNameFromArgument = arguments?.getString(BOOK_AUTHOR_ARG).toString()
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
                    selectedAuthorBookStyle = list[i].bookAuthorStyle
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

    //TODO запрос не возвращает ответ с заданным параметром имени и фамиилл автора
    private fun getAuthorBooks() {
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

    //TODO переделать метод
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
        binding.bookAuthorBooksNumber.text = setAuthorBooksCountField(authorBooks.size)
        val adapter = authorBooks.let { RelatedBooksAdapter(context, it, this) }
        binding.bookAuthorAllBooksRecyclerView.adapter = adapter
    }

    private fun setAuthorBooksCountField(authorBooksSize: Int): String {
        val sb = StringBuilder()
        sb.append(authorBooksSize).append(" ").append("книги")
        return sb.toString()
    }

    private fun loadRelatedAuthors() {
        NetworkClient.buildBookAuthorApi().getBookAuthor().enqueue(
            object : Callback<List<BookAuthorModel>> {
                override fun onResponse(
                    call: Call<List<BookAuthorModel>>,
                    response: Response<List<BookAuthorModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        getRelatedAuthors(response)
                    }
                }

                override fun onFailure(call: Call<List<BookAuthorModel>>, t: Throwable) {
                    showToast(FAILED_TO_LOAD_RELATED_AUTHORS)
                }
            }
        )
    }

    private fun getRelatedAuthors(response: Response<List<BookAuthorModel>>) {
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.bookAuthorRelatedAuthorsRecyclerView.layoutManager = linearManager
        val list = response.body()
        val listOfRelatedAuthors: MutableList<BookAuthorModel> = mutableListOf()
        if (list != null) {
            for (i in list.indices) {
                if (list[i].bookAuthorStyle == selectedAuthorBookStyle) {
                    listOfRelatedAuthors.add(list[i])
                }
            }
        }
        val adapter = RelatedAuthorsAdapter(context, listOfRelatedAuthors, this)
        binding.bookAuthorRelatedAuthorsRecyclerView.adapter = adapter
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

    override fun imageClicked(model: BookAuthorModel) {
        changeFragment(getInstance(model.bookAuthor))
    }
}