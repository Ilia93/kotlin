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

    lateinit var binding: SelectedBookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectedBookFragmentBinding.inflate(inflater, container, false)
        setNumber()
        return binding.root
    }

    private fun setNumber() {
        val builder = StringBuilder()
        builder.append("100").append(" ").append(binding.selectedBookReadersNumber.text)
        binding.selectedBookReadersNumber.text = builder.toString()
    }
}