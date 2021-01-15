package com.example.pocketbook.screen.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.SelectedBookFragmentBinding

class SelectedBookFragment : Fragment() {

    companion object {

        fun getInstance(): SelectedBookFragment {
            return SelectedBookFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SelectedBookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}