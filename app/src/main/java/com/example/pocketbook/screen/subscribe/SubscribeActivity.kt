package com.example.pocketbook.screen.subscribe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketbook.databinding.SubscribeScreenActivityBinding

class SubscribeActivity : AppCompatActivity() {

    private lateinit var binding: SubscribeScreenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SubscribeScreenActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}