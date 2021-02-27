package com.example.pocketbook.presentation.screen.search.book_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.SearchScreenSelectedCategoryFragmentBinding
import com.example.pocketbook.presentation.screen.book.SelectedBookFragment
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.search.books_styles.BooksStylesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment(), ItemListener<BookModel> {

    companion object {
        fun getInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

    private lateinit var binding: SearchScreenSelectedCategoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchScreenSelectedCategoryFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        loadServerData()
        return view
    }

    private fun setClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            changeFragment(BooksStylesFragment.getInstance())
        }
    }

    private fun loadServerData() {
        NetworkClient.buildBookApiClient().getBooks().enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    setRecyclerView(response)
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    showMessage(LOAD_ERROR)
                }

            }
        )
    }

    private fun setRecyclerView(response: Response<List<BookModel>>) {
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.selectedCategoryRecyclerView.layoutManager = linearManager
        val list = response.body()
        val adapter = list?.let { BooksCategoryAdapter(context, this, it) }
        binding.selectedCategoryRecyclerView.adapter = adapter
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_frame, fragment)
            ?.commit()
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
                model.bookSeries,
                model.marksCount
            )
        )
    }
}