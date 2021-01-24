package com.example.pocketbook.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.databinding.SearchScreenFragmentBinding
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.search.recycler_view.books.BooksAdapter
import com.example.pocketbook.screen.search.recycler_view.books.BooksDataClass
import com.example.pocketbook.screen.search.recycler_view.books_categories.BooksCategoriesAdapter
import com.example.pocketbook.screen.search.recycler_view.books_categories.BooksCategoriesDataClass

class SearchScreenFragment : Fragment(), ItemListener<BooksDataClass> {
    companion object {
        fun getInstance(): SearchScreenFragment {
            return SearchScreenFragment()
        }
    }

    private lateinit var binding: SearchScreenFragmentBinding
    private var listOfBooks = mutableListOf<BooksDataClass>()
    private var listOfBooksCategories = mutableListOf<BooksCategoriesDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchScreenFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        createBooksData()
        createBooksCategoriesData()
        setBooksRecyclerView()
        setBooksCategoriesRecyclerView()
        return view
    }

    private fun createBooksData() {
        addListOfBooksItem(0, 0, R.drawable.ic_search_screen_headphones_24, "Аудиокниги")
        addListOfBooksItem(1, 0, R.drawable.ic_search_screen_free_books_24, "Бесплатные книги")
        addListOfBooksItem(2, 0, R.drawable.ic_search_screen_podcast_24, "Подкасты")
        addListOfBooksItem(3, 0, R.drawable.ic_my_books_read_24, "Читай и слушай")
    }

    private fun addListOfBooksItem(index: Int, booksCount: Int, vectorImage: Int, books: String) {
        listOfBooks.add(
            index,
            BooksDataClass(
                booksCount,
                ResourcesCompat.getDrawable(resources, vectorImage, null),
                books
            )
        )

    }

    private fun createBooksCategoriesData() {
        addListOfBooksCategoriesItem(0, "Приключения", 0)
        addListOfBooksCategoriesItem(1, "Роман", 0)
        addListOfBooksCategoriesItem(2, "История", 0)
        addListOfBooksCategoriesItem(3, "Утопия", 0)
        addListOfBooksCategoriesItem(4, "Зарубежное", 0)
    }

    private fun addListOfBooksCategoriesItem(index: Int, category: String, booksCount: Int) {
        listOfBooksCategories.add(
            index,
            BooksCategoriesDataClass(
                category,
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                booksCount
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
        val adapter = context?.let { BooksCategoriesAdapter(it, listOfBooksCategories) }
        binding.searchScreenBookCategoriesRecyclerView.adapter = adapter
    }

    override fun itemClicked(model: BooksDataClass) {

    }
}