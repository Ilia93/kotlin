package com.example.pocketbook.screen.my_books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.BookModel
import com.example.pocketbook.databinding.MyBooksFragmentBinding
import com.example.pocketbook.screen.book.SelectedBookFragment
import com.example.pocketbook.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.screen.main.top.ItemListener
import com.example.pocketbook.screen.my_books.info.MyBooksInfoFragment
import com.example.pocketbook.screen.my_books.my_shelf.CreatedShelfFragment
import com.example.pocketbook.screen.my_books.recycler_view.MyBooksListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyBooksFragment : Fragment(), ItemListener<BookModel> {
    companion object {
        fun getInstance(): MyBooksFragment {
            return MyBooksFragment()
        }

        const val SUB_TITLE_STRING_KEY = "subTitleKey"
        const val TITLE_STRING_KEY = "titleKey"
    }

    private val FINISHED = "Прочитал"
    private val MAIN_FRAME = R.id.main_frame
    private val MY_BOOKS_FRAME = R.id.my_books_shelves_frame
    private val supportFragmentManager: FragmentManager? = activity?.supportFragmentManager
    private val OFFLINE = "Доступны оффлайн"
    private val READ = "Читаю"
    private val WANT_TO_READ = "Хочу прочитать"
    private val NO_BOOKS_AVAILABLE = "Нет книг"
    private lateinit var binding: MyBooksFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyBooksFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        return view
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.getBoolean("shelf_name_true") == true) {
            changeFragment(CreatedShelfFragment.getInstance(), MY_BOOKS_FRAME, null, null)
        }
        showBooks()
    }

    private fun setClickListeners() {
        binding.myBooksAvailableOffline.setOnClickListener(View.OnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                MAIN_FRAME,
                OFFLINE,
                binding.myBooksOfflineCount.text as String
            )
        })
        binding.myBooksWantToRead.setOnClickListener(View.OnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                MAIN_FRAME,
                WANT_TO_READ,
                binding.myBooksWantReadCount.text as String
            )
        })
        binding.myBooksRead.setOnClickListener(View.OnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                MAIN_FRAME, READ, binding.myBooksReadCount.text as String
            )
        })
        binding.myBooksFinished.setOnClickListener(View.OnClickListener {
            changeFragment(
                MyBooksInfoFragment.getInstance(),
                MAIN_FRAME,
                FINISHED,
                binding.myBooksFinishedCount.text as String
            )
        })
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
        val adapter = list?.let { MyBooksListAdapter(context, this, it) }
        binding.myBooksTopRecyclerView.adapter = adapter

    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun changeFragment(fragment: Fragment, frame: Int, title: String?, subTitle: String?) {
        val arguments = Bundle()
        arguments.putString(TITLE_STRING_KEY, title)
        if (subTitle.equals("0")) {
            arguments.putString(SUB_TITLE_STRING_KEY, NO_BOOKS_AVAILABLE)
        } else {
            arguments.putString(SUB_TITLE_STRING_KEY, subTitle)
        }
        supportFragmentManager?.beginTransaction()?.replace(frame, fragment)?.commit()
    }

    override fun itemClicked(model: BookModel) {
        val supportFragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_frame, SelectedBookFragment.getInstance())
        fragmentTransaction?.setTransition(TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction?.commit()
    }
}