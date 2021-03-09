package com.example.pocketbook.presentation.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.pocketbook.R
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.api.ImageCollectionApi
import com.example.pocketbook.data.network.model.ImageCollectionModel
import com.example.pocketbook.databinding.MainFragmentBinding
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.presentation.screen.main.tabs.PagesAdapter
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionFragment
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BACKGROUND_IMAGE_URL
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.BUTTON_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.HEADER_TEXT
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.IMAGE_ID
import com.example.pocketbook.presentation.screen.main.top.ImageCollectionItem.Companion.TAB_TEXT
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.main.top.TopScreenAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), ItemListener<ImageCollectionModel> {

    companion object {


        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, viewGroup, false)
        showBookCollections()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setViewPage()
    }

    private fun setViewPage() {
        val mainViewPager: ViewPager = binding.mainFragmentViewPager
        val pagesAdapter = fragmentManager?.let { PagesAdapter(it) }
        mainViewPager.adapter = pagesAdapter
        binding.mainFragmentTabLayout.setupWithViewPager(mainViewPager)
    }

    private fun showBookCollections() {
        NetworkClient.buildRetrofitClient(ImageCollectionApi::class.java).getUrls().enqueue(
            object : Callback<List<ImageCollectionModel>> {
                override fun onResponse(
                    call: Call<List<ImageCollectionModel>>,
                    response: Response<List<ImageCollectionModel>>
                ) {
                    if (response.isSuccessful) {
                        setRecyclerViewAdapter(response)
                    } else {
                        showToast(DATA_FAIL)
                    }
                }

                override fun onFailure(call: Call<List<ImageCollectionModel>>, t: Throwable) {
                    showToast(LOAD_ERROR)
                }
            }
        )
    }

    private fun setRecyclerViewAdapter(response: Response<List<ImageCollectionModel>>) {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.mainFragmentTopRecyclerView.layoutManager = linearLayoutManager
        binding.mainFragmentTopRecyclerView.setHasFixedSize(true)
        val list: List<ImageCollectionModel> = response.body()!!
        val recyclerViewAdapter = TopScreenAdapter(context, this, list)
        binding.mainFragmentTopRecyclerView.adapter = recyclerViewAdapter
    }

    private fun showToast(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(model: ImageCollectionModel) {
        val fragment = ImageCollectionFragment()
        fragment.arguments?.apply {
            putString(IMAGE_ID, model.id)
            putString(TAB_TEXT, model.tabText)
            putString(HEADER_TEXT, model.headerText)
            putString(BUTTON_TEXT, model.buttonText)
            putString(BACKGROUND_IMAGE_URL, model.backGroundImageUrl)
        }
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_frame, fragment)
            ?.commit()
    }
}