package com.example.pocketbook.screen.my_books.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MyBooksBooksInfoFragmentBinding

class MyBooksInfoFragment : Fragment() {

    companion object {
        fun getInstance(): MyBooksInfoFragment {
            return MyBooksInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MyBooksBooksInfoFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}