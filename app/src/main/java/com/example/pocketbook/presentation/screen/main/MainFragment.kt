package com.example.pocketbook.presentation.screen.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.pocketbook.data.NetworkClient
import com.example.pocketbook.data.network.api.ImageCollectionApi
import com.example.pocketbook.data.network.model.ImageCollectionModel
import com.example.pocketbook.databinding.MainFragmentBinding
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.DATA_FAIL
import com.example.pocketbook.presentation.screen.main.MainActivity.Companion.LOAD_ERROR
import com.example.pocketbook.presentation.screen.main.tabs.PagesAdapter
import com.example.pocketbook.presentation.screen.main.top.ItemListener
import com.example.pocketbook.presentation.screen.main.top.ScreenPage
import com.example.pocketbook.presentation.screen.main.top.TopScreenAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), ItemListener<ImageCollectionModel> {

    companion object {
        private const val BACKGROUND_IMAGE_URL = "backGroundUrl"
        private const val BUTTON_TEXT = "buttonText"
        private const val HEADER_TEXT = "headerText"
        private const val IMAGE_ID = "imgId"
        private const val TAB_TEXT = "tabText"

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
        val intent = Intent(context, ScreenPage::class.java)
        intent.putExtra(IMAGE_ID, model.id)
        intent.putExtra(TAB_TEXT, model.tabText)
        intent.putExtra(HEADER_TEXT, model.headerText)
        intent.putExtra(BUTTON_TEXT, model.buttonText)
        intent.putExtra(BACKGROUND_IMAGE_URL, model.backGroundImageUrl)
        context?.startActivity(intent)
    }
}