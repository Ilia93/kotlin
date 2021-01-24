package com.example.pocketbook.screen.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.pocketbook.R
import com.example.pocketbook.databinding.MainActivityBinding
import com.example.pocketbook.screen.my_books.MyBooksFragment
import com.example.pocketbook.screen.profile.UserProfileFragment
import com.example.pocketbook.screen.search.SearchScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOAD_ERROR = "Ошибка подключения к интернету. Проверьте подключение"
        const val DATA_FAIL = "Ошибка получения данных"
    }

    private lateinit var binding: MainActivityBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = MainActivityBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        if (bundle == null) {
            changeFragment(MainFragment.getInstance())
        }
        bottomNavigation = binding.bottomNavigationBar
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )
    }

    private fun changeFragment(fragment: Fragment) {
        val replace: FragmentTransaction =
            fragmentManager.beginTransaction().replace(R.id.main_frame, fragment)
        replace.addToBackStack(null).commit()
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bottom_nav_home -> {
                    changeFragment(MainFragment.getInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_my_books -> {
                    changeFragment(MyBooksFragment.getInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_search -> {
                    changeFragment(SearchScreenFragment.getInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_profile -> {
                    changeFragment(UserProfileFragment.getInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}