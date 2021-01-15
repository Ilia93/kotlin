package com.example.pocketbook.screen.my_books.my_shelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MyBooksShelfDefaultFragmentBinding

class DefaultShelfFragment : Fragment() {

    companion object {
        fun getInstance(): DefaultShelfFragment {
            return DefaultShelfFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MyBooksShelfDefaultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}