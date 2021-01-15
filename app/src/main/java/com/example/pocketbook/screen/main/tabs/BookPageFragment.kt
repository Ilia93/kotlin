package com.example.pocketbook.screen.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MainBooksTabItemTypeOneBinding

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
        val binding = MainBooksTabItemTypeOneBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
}