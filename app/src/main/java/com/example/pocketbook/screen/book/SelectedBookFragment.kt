package com.example.pocketbook.screen.book

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.SelectedBookFragmentBinding
import com.example.pocketbook.screen.subscribe.SubscribeActivity

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
        setOnClickListeners()
        return binding.root
    }

    private fun setOnClickListeners() {
        binding.selectedBookSubscribeBtn.setOnClickListener {
            val intent = Intent(activity, SubscribeActivity::class.java)
            startActivity(intent)
        }
    }
}