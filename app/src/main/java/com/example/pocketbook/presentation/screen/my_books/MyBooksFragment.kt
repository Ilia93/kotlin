package com.example.pocketbook.presentation.screen.my_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.MyBooksFragmentBinding
import com.example.pocketbook.presentation.screen.book.SelectedBookFragment
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.my_books.books_status.BooksStatusAdapter
import com.example.pocketbook.presentation.screen.my_books.books_status.BooksStatusDataClass
import com.example.pocketbook.presentation.screen.my_books.info.MyBooksInfoFragment
import com.example.pocketbook.presentation.screen.my_books.my_shelf.CreatedShelfFragment
import com.example.pocketbook.presentation.screen.my_books.my_shelf.DefaultShelfFragment
import com.example.pocketbook.presentation.screen.my_books.top.MyBooksTopListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBooksFragment : Fragment(), ItemListener<BookModel>,
    BookMenuListener<BooksStatusDataClass> {
    companion object {
        fun getInstance(): MyBooksFragment {
            return MyBooksFragment()
        }

        const val SUB_TITLE_STRING_KEY = "subTitleKey"
        const val TITLE_STRING_KEY = "titleKey"
    }

    private val FINISHED = "Прочитал"
    private val OFFLINE = "Доступны оффлайн"
    private val READ = "Читаю"
    private val WANT_TO_READ = "Хочу прочитать"
    private val NO_BOOKS_AVAILABLE = "Нет книг"
    private val ALL_USER_BOOKS = "Все книги и аудиокниги"
    private var menuMutableList: MutableList<BooksStatusDataClass> = mutableListOf()
    private lateinit var binding: MyBooksFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyBooksFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        setBooksStatusRecyclerView()
        getAllUserBooksCount()
        return view
    }

    private fun setClickListeners() {
        binding.myBooksAvailableOffline.setOnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                R.id.main_frame,
                OFFLINE,
                binding.myBooksOfflineCount.text as String
            )
        }
        binding.myBooksAllUserBooks.setOnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                R.id.main_frame,
                ALL_USER_BOOKS,
                binding.myBooksAllUserBooks.text.toString()
            )
        }
    }

    private fun changeFragment(fragment: Fragment, frame: Int, title: String?, subTitle: String?) {
        val arguments = Bundle()
        arguments.putString(TITLE_STRING_KEY, title)
        if (subTitle.equals("0")) {
            arguments.putString(SUB_TITLE_STRING_KEY, NO_BOOKS_AVAILABLE)
        } else {
            arguments.putString(SUB_TITLE_STRING_KEY, subTitle)
        }
        fragment.arguments = arguments
        activity?.supportFragmentManager?.beginTransaction()?.replace(frame, fragment)
            ?.commit()
    }

    private fun setBooksStatusRecyclerView() {
        val linearManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.myBooksBooksStatusRecyclerView.layoutManager = linearManager
        val adapter = BooksStatusAdapter(context, this, initializeBooksStatus())
        val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null)?.let {
            itemDecoration.setDrawable(
                it
            )
        }
        binding.myBooksBooksStatusRecyclerView.addItemDecoration(itemDecoration)
        binding.myBooksBooksStatusRecyclerView.adapter = adapter
    }

    private fun getAllUserBooksCount() {
        NetworkClient.buildBookApiClient().getBooksCount().enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        getCount(response)
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    showMessage(DATA_FAIL)
                }
            }
        )
    }

    private fun getCount(response: Response<String>) {
        val builder = StringBuilder()
        val count = response.body()
        builder.append("Все ").append(count)
        binding.myBooksAllUserBooks.text = builder.toString()

    }

    override fun onResume() {
        super.onResume()
        val bundle = arguments
        if (bundle != null) {
            changeShelfFragment(CreatedShelfFragment.getInstance())
        } else {
            changeShelfFragment(DefaultShelfFragment.getInstance())
        }
        showBooks()

    }

    private fun changeShelfFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.my_books_shelves_frame, fragment)
            ?.commit()
    }

    private fun showBooks() {
        NetworkClient.buildBookApiClient().getBooks().enqueue(
            object : Callback<List<BookModel>> {
                override fun onResponse(
                    call: Call<List<BookModel>>,
                    response: Response<List<BookModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        loadRecyclerViewItems(response)
                    } else {
                        showToast(DATA_FAIL)
                    }
                }

                override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                    showToast(LOAD_ERROR)
                }
            }
        )
    }

    private fun loadRecyclerViewItems(response: Response<List<BookModel>>) {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.myBooksTopRecyclerView.layoutManager = linearLayoutManager
        val list: List<BookModel>? = response.body()
        val adapter = list?.let { MyBooksTopListAdapter(context, this, it) }
        binding.myBooksTopRecyclerView.adapter = adapter

    }

    private fun initializeBooksStatus(): MutableList<BooksStatusDataClass> {
        menuMutableList.add(
            0,
            BooksStatusDataClass(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.ic_my_books_want_read_24, null
                ),
                WANT_TO_READ, 0
            )
        )
        menuMutableList.add(
            1,
            BooksStatusDataClass(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_my_books_read_24, null),
                READ, 0
            )
        )
        menuMutableList.add(
            2,
            BooksStatusDataClass(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_my_books_finish_read_24, null),
                FINISHED, 0
            )
        )
        return menuMutableList
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(model: BookModel) {
        val supportFragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.main_frame,
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
        fragmentTransaction?.setTransition(TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction?.commit()
    }

    override fun menuClicked(model: BooksStatusDataClass) {
        changeFragment(
            MyBooksInfoFragment.getInstance(),
            R.id.main_frame,
            model.statusText,
            model.booksStatusCount.toString()
        )
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}