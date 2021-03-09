package com.example.pocketbook.presentation.screen.main.top
/*
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.model.ImageCollectionModel
import com.example.pocketbook.databinding.ScreenPageActivityBinding
import com.example.pocketbook.presentation.screen.main.MainActivity
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScreenPage : AppCompatActivity() {

    private lateinit var binding: ScreenPageActivityBinding


    private fun loadImage() {
        NetworkClient.buildImageCollectionClient().getUrls()
            .enqueue(object : Callback<List<ImageCollectionModel>> {
                override fun onResponse(
                    call: Call<List<ImageCollectionModel>>,
                    response: Response<List<ImageCollectionModel>>
                ) {
                    setBackGroundImage(response.body())
                }

                override fun onFailure(call: Call<List<ImageCollectionModel>>, t: Throwable) {
                    showToast(LOAD_ERROR)
                }

            })
    }

    private fun setBackGroundImage(list: List<ImageCollectionModel>?) {
        if (list != null) {
            val imageId = intent.getStringExtra("imgId")
            for (i in list.indices) {
                if (imageId == list[i].id) {
                    if (list[i].backGroundImageUrl != null) {
                        Glide.with(applicationContext).load(list[i].backGroundImageUrl)
                            .into(binding.imageActivityImg)
                        loadItems()
                    } else if (list[i].backGroundImageUrl == null) {
                        Glide.with(applicationContext).load(list[i].imageUrl)
                            .into(binding.imageActivityImg)
                        loadItems()
                    }
                }
            }
        }
    }

    private fun loadItems() {
        binding.imageActivityTextTag.text = intent.getStringExtra("headerText")
        binding.imageActivityText.text = intent.getStringExtra("tabText")
        binding.imageScreenActionButton.text = intent.getStringExtra("buttonText")
    }

    private fun setButtonClickListeners() {
        binding.imageScreenCloseBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.imageScreenActionButton.setOnClickListener {
            showToast("Здесь пока пусто")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ScreenPageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonClickListeners()
        loadImage()
    }

    override fun onResume() {
        super.onResume()
        val ofInt = ObjectAnimator.ofInt(binding.imageActivityProgressBar, "progress", 0, 100)
        ofInt.interpolator = AccelerateInterpolator()
        ofInt.duration = 5000L
        ofInt.start()
    }
}
*/