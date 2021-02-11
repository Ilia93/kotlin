package com.example.pocketbook.screen.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketbook.databinding.SplashScreenActivityBinding
import com.example.pocketbook.screen.main.MainActivity
import java.lang.ref.WeakReference


class SplashScreen : AppCompatActivity() {
    private val timer = 4000
    private var handler: Handler? = Handler(Looper.getMainLooper())

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        val binding: SplashScreenActivityBinding =
            SplashScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler?.postDelayed(MemoryLeak(context = applicationContext), timer.toLong())
    }

    private class MemoryLeak(val context: Context) : Runnable {
        private val weakReference: WeakReference<Context> = WeakReference(context)

        override fun run() {
            val context: Context? = weakReference.get()
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }
}
