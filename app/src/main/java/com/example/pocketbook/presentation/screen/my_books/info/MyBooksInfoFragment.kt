package com.example.pocketbook.presentation.screen.my_books.info

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
import com.example.pocketbook.databinding.MyBooksBooksInfoFragmentBinding
import com.example.pocketbook.presentation.screen.book.SelectedBookFragment
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.my_books.MyBooksFragment
import com.example.pocketbook.presentation.screen.my_books.MyBooksFragment.Companion.SUB_TITLE_STRING_KEY
import com.example.pocketbook.presentation.screen.my_books.MyBooksFragment.Companion.TITLE_STRING_KEY
import com.example.pocketbook.presentation.screen.my_books.info.recycler_view.MyBooksInfoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBooksInfoFragment : Fragment(), ItemListener<BookModel> {

    companion object {
        fun getInstance(): MyBooksInfoFragment {
            return MyBooksInfoFragment()
        }
    }

    private lateinit var binding: MyBooksBooksInfoFragmentBinding
    private val listOfBooks: MutableList<BookModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyBooksBooksInfoFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        loadBooks()
        return view
    }

    private fun setClickListeners() {
        binding.myBooksInfoToolbar.setNavigationOnClickListener(View.OnClickListener {
            changeFragment(MyBooksFragment.getInstance())
        })
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_frame, fragment)
            ?.addToBackStack("selected")?.commit()
    }

    private fun loadBooks() {
        NetworkClient.buildBookApiClient().getBooks().enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        setRecyclerView(response)
                    }
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    showMessage(DATA_FAIL)
                }

            }
        )
    }

    private fun setRecyclerView(response: Response<List<BookModel>>) {
        response.body()?.let { listOfBooks.addAll(it) }
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.myBooksInfoRecyclerView.layoutManager = linearManager
        val adapter = MyBooksInfoAdapter(context, this, listOfBooks)
        binding.myBooksInfoRecyclerView.adapter = adapter
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val title = arguments?.get(TITLE_STRING_KEY).toString()
        val subTitle = arguments?.get(SUB_TITLE_STRING_KEY).toString()
        binding.myBooksInfoToolbarTag.text = title
        binding.myBooksInfoToolbarBooksCount.text = subTitle
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