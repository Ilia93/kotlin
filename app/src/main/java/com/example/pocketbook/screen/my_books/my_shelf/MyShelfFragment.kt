package com.example.pocketbook.screen.my_books.my_shelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.example.pocketbook.R
import com.example.pocketbook.databinding.MyShelfFragmentBinding
import com.example.pocketbook.screen.my_books.MyBooksFragment

class MyShelfFragment : Fragment() {

    companion object {
        fun getInstance(): MyShelfFragment {
            return MyShelfFragment()
        }

        const val SHELF_NAME_NOT_EMPTY = "1"
    }

    private val EMPTY_FIELD = "Имя полки не может быть пустым"

    private lateinit var binding: MyShelfFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyShelfFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickListeners()
        return view
    }

    private fun setClickListeners() {
        binding.myShelfToolbarReady.setOnClickListener(View.OnClickListener {
            val userInput: String = binding.myShelfNameField.text.toString()
            if (userInput.equals("")) {
                showToast(EMPTY_FIELD)
            } else {
                changeFragment(MyBooksFragment.getInstance())
            }
        })
        binding.myShelfLoadPoster.setOnClickListener(View.OnClickListener {
            showToast("Здесь пока пусто")
        })
    }

    private fun changeFragment(fragment: Fragment) {
        val arguments = Bundle()
        arguments.putString(SHELF_NAME_NOT_EMPTY, binding.myShelfNameField.text.toString())
        fragment.arguments = arguments
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_frame, fragment)
            ?.setTransition(TRANSIT_FRAGMENT_OPEN)
            ?.commit()
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}