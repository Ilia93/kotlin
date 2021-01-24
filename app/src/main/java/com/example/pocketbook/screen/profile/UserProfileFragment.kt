package com.example.pocketbook.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pocketbook.R
import com.example.pocketbook.databinding.ProfileFragmentBinding
import com.example.pocketbook.screen.profile.recycler_view.UserProfileAdapter
import com.example.pocketbook.screen.profile.recycler_view.UserProfileDataClass


class UserProfileFragment : Fragment() {

    companion object {
        fun getInstance(): UserProfileFragment {
            return UserProfileFragment()
        }

        const val BOOKS = "книги"
        const val ELAPSED_TIME = "Времени за чтением"
        const val MINUTES = "минут"
        const val PAGES = "стр"
        const val PAGES_PER_HOUR = "стр/час"
        const val READ = "Прочитано"
        const val READING_SPEED = "Скорость чтения"
    }

    private lateinit var binding: ProfileFragmentBinding
    private var listOfMenu = mutableListOf<UserProfileDataClass>()
    private var imageUrlTop =
        "https://p1.hiclipart.com/preview/311/118/404/white-and-black-owl-painting-png-clipart.jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecyclerViewData()
        initRecyclerView()
        return view
    }

    private fun initRecyclerViewData() {
        addItem(0, READ, BOOKS, R.drawable.ic_launcher_background, "10")
        addItem(1, READ, PAGES, R.drawable.ic_launcher_background, "0")
        addItem(2, READING_SPEED, PAGES_PER_HOUR, R.drawable.ic_launcher_background, "0")
        addItem(3, ELAPSED_TIME, MINUTES, R.drawable.ic_launcher_background, "10")
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.myProfileRecyclerView.layoutManager = linearLayoutManager
        val adapter = UserProfileAdapter(context, listOfMenu)
        binding.myProfileRecyclerView.adapter = adapter
    }

    private fun addItem(
        categoryData: Int,
        bookTag: String,
        bookData: String,
        menuImage: Int,
        itemCount: String
    ) {
        ResourcesCompat.getDrawable(resources, menuImage, null)?.let {
            UserProfileDataClass(
                bookTag, bookData,
                it, itemCount
            )
        }?.let {
            listOfMenu.add(
                categoryData,
                it
            )
        }
    }

    override fun onStart() {
        super.onStart()
        context?.let {
            Glide.with(it).load(imageUrlTop).into(binding.myProfileUserImage)
        }
    }
}