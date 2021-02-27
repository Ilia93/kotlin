package com.example.pocketbook.presentation.screen.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MainBooksTabFragmentBinding
import com.example.pocketbook.databinding.MainBooksTabItemCollectionTypeBinding

class BookPageFragment : Fragment() {
    companion object {
        fun getInstance(): BookPageFragment {
            return BookPageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            MainBooksTabFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
}