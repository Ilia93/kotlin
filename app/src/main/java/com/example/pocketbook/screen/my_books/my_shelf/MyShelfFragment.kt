package com.example.pocketbook.screen.my_books.my_shelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MyShelfFragmentBinding

class MyShelfFragment : Fragment() {

    companion object {
        fun getInstance(): MyShelfFragment {
            return MyShelfFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MyShelfFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}