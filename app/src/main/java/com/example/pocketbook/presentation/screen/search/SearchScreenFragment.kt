package com.example.pocketbook.presentation.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.SearchScreenFragmentBinding
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.search.books_styles.BooksStylesFragment
import com.example.pocketbook.presentation.screen.search.recycler_view.books.BooksAdapter
import com.example.pocketbook.presentation.screen.search.recycler_view.books.BooksDataClass
import com.example.pocketbook.presentation.screen.search.recycler_view.books_styles.BooksStylesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenFragment : Fragment(), ItemListener<BooksDataClass> {
    companion object {
        fun getInstance(): SearchScreenFragment {
            return SearchScreenFragment()
        }

        const val CATEGORY_ADVENTURE = "Приключения"
        const val CATEGORY_NOVEL = "Роман"
        const val CATEGORY_HISTORY = "История"
        const val CATEGORY_UTOPIA = "Утопия"
        const val CATEGORY_FOREIGN = "Зарубежное"
        const val CATEGORY_FICTION = "Фантастика"
        const val CATEGORY_DETECTIVE = "Детективы"
        const val CATEGORY_CHILDREN = "Детские книги"
        const val CATEGORY_CLASSICAL = "Классическая литература"
    }

    private val KEY_TAG = "TAG"
    private lateinit var binding: SearchScreenFragmentBinding
    private var booksCount: String = ""
    private var listOfBooks = mutableListOf<BooksDataClass>()
    private var listOfBooksCategories = mutableListOf<BooksDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchScreenFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        getBooksCount()
        getBooksDataFromServer()
        createBooksCategoriesData()
        setBooksRecyclerView()
        setBooksCategoriesRecyclerView()
        return view
    }

    private fun createBooksData(count: String) {
        addListOfBooksItem(0, 0, R.drawable.ic_search_screen_headphones_24, "Аудиокниги")
        addListOfBooksItem(
            1,
            count.toInt(),
            R.drawable.ic_search_screen_free_books_24,
            "Бесплатные книги"
        )
        addListOfBooksItem(2, 0, R.drawable.ic_search_screen_podcast_24, "Подкасты")
        addListOfBooksItem(3, 0, R.drawable.ic_my_books_read_24, "Читай и слушай")
    }

    private fun addListOfBooksItem(
        index: Int,
        booksCount: Int,
        vectorImage: Int,
        category: String
    ) {
        listOfBooks.add(
            index,
            BooksDataClass(
                booksCount,
                ResourcesCompat.getDrawable(resources, vectorImage, null),
                category
            )
        )

    }

    private fun createBooksCategoriesData() {
        addListOfBooksCategoriesItem(0, 0, R.drawable.ic_launcher_background, CATEGORY_ADVENTURE)
        addListOfBooksCategoriesItem(1, 0, R.drawable.ic_launcher_background, CATEGORY_NOVEL)
        addListOfBooksCategoriesItem(2, 0, R.drawable.ic_launcher_background, CATEGORY_HISTORY)
        addListOfBooksCategoriesItem(3, 0, R.drawable.ic_launcher_background, CATEGORY_UTOPIA)
        addListOfBooksCategoriesItem(4, 0, R.drawable.ic_launcher_background, CATEGORY_FOREIGN)
        addListOfBooksCategoriesItem(5, 0, R.drawable.ic_launcher_background, CATEGORY_FICTION)
        addListOfBooksCategoriesItem(6, 0, R.drawable.ic_launcher_background, CATEGORY_DETECTIVE)
        addListOfBooksCategoriesItem(7, 0, R.drawable.ic_launcher_background, CATEGORY_CHILDREN)
        addListOfBooksCategoriesItem(8, 0, R.drawable.ic_launcher_background, CATEGORY_CLASSICAL)
    }

    private fun addListOfBooksCategoriesItem(
        index: Int,
        booksCount: Int,
        vectorImage: Int,
        category: String
    ) {
        listOfBooksCategories.add(
            index,
            BooksDataClass(
                booksCount,
                ResourcesCompat.getDrawable(resources, vectorImage, null),
                category
            )
        )
    }

    private fun setBooksRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchScreenBooksRecyclerView.layoutManager = layoutManager
        val adapter = context?.let { BooksAdapter(it, listOfBooks, this) }
        binding.searchScreenBooksRecyclerView.adapter = adapter
        val divider = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null)?.let {
            divider.setDrawable(
                it
            )
        }
        binding.searchScreenBooksRecyclerView.addItemDecoration(divider)
    }

    private fun setBooksCategoriesRecyclerView() {
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchScreenBookCategoriesRecyclerView.layoutManager = linearLayout
        val adapter = context?.let { BooksStylesAdapter(it, listOfBooksCategories, this) }
        binding.searchScreenBookCategoriesRecyclerView.adapter = adapter
    }

    private fun getBooksDataFromServer() {
        NetworkClient.buildBookApiClient().getBooks().enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    getCategoriesAndBooksCount(response)
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    showMessage(DATA_FAIL)
                }
            }
        )
    }

    private fun getCategoriesAndBooksCount(response: Response<List<BookModel>>) {
        val listOfCategories = listOf(response.body())
        for (i in listOfCategories) {
        }
    }

    private fun getBooksCount() {
        NetworkClient.buildBookApiClient().getBooksCount().enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        getCount(response)
                        showMessage("Ok")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    showMessage(DATA_FAIL)
                }
            }
        )
    }

    private fun getCount(response: Response<String>) {
        booksCount = response.body().toString()
        createBooksData(booksCount)
    }

    override fun itemClicked(model: BooksDataClass) {
        val arguments = Bundle()
        val fragment = BooksStylesFragment.getInstance()
        fragment.arguments = arguments
        arguments.putString(KEY_TAG, model.category)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frame, fragment)?.commit()
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}