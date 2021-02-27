package com.example.pocketbook.presentation.screen.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pocketbook.databinding.MainAudioBookTabFragmentBinding

class AudioBookPageFragment : Fragment() {
    companion object {
        fun getInstance(): AudioBookPageFragment {
            return AudioBookPageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainAudioBookTabFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }
}