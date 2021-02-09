package com.example.pocketbook.screen.search.books_styles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketbook.R
import com.example.pocketbook.databinding.SearchScreenBooksCategoriesFragmentBinding
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.search.SearchScreenFragment
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_ADVENTURE
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_CHILDREN
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_CLASSICAL
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_DETECTIVE
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_FICTION
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_FOREIGN
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_HISTORY
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_NOVEL
import com.example.pocketbook.screen.search.SearchScreenFragment.Companion.CATEGORY_UTOPIA
import com.example.pocketbook.screen.search.books_styles.recycler_view.BooksStylesAdapter
import com.example.pocketbook.screen.search.books_styles.recycler_view.BooksStylesDataClass
import com.example.pocketbook.screen.search.book_category.CategoryFragment

class BooksStylesFragment : Fragment(), ItemListener<BooksStylesDataClass> {

    companion object {
        fun getInstance(): BooksStylesFragment {
            return BooksStylesFragment()
        }
    }

    private val listOfItems = mutableListOf<BooksStylesDataClass>()

    private lateinit var binding: SearchScreenBooksCategoriesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchScreenBooksCategoriesFragmentBinding
            .inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        setRecyclerViewItems()
        setRecyclerView()
        return view
    }

    override fun onResume() {
        super.onResume()
        binding.booksCategoriesTag.text = arguments?.getString("TAG")
    }

    private fun setClickListeners() {
        binding.searchScreenToolbar.setNavigationOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_frame, SearchScreenFragment.getInstance())?.commit()
        })
    }

    private fun setRecyclerView() {
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.booksCategoriesRecyclerView.layoutManager = linearLayout
        val adapter = BooksStylesAdapter(context, listOfItems, this)
        binding.booksCategoriesRecyclerView.adapter = adapter
    }

    private fun setRecyclerViewItems() {
        addItem(0, CATEGORY_ADVENTURE, "0")
        addItem(1, CATEGORY_NOVEL, "0")
        addItem(2, CATEGORY_HISTORY, "0")
        addItem(3, CATEGORY_UTOPIA, "0")
        addItem(4, CATEGORY_FOREIGN, "0")
        addItem(5, CATEGORY_FICTION, "0")
        addItem(6, CATEGORY_DETECTIVE, "0")
        addItem(7, CATEGORY_CHILDREN, "0")
        addItem(8, CATEGORY_CLASSICAL, "0")
    }

    private fun addItem(index: Int, categoryTag: String, categoryCount: String) {
        val stringBuilder = StringBuilder()
        listOfItems.add(
            index,
            BooksStylesDataClass(
                categoryTag,
                stringBuilder.append(categoryCount).append(" книг").toString()
            )
        )
    }

    override fun itemClicked(model: BooksStylesDataClass) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frame, CategoryFragment.getInstance())?.commit()
    }
}
