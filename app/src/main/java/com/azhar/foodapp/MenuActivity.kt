package com.azhar.foodapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.azhar.foodapp.adapters.ViewPagerAdapter
import com.azhar.foodapp.databinding.ActivityMenuBinding
import com.azhar.foodapp.ui.search.SearchFoodFragment
import com.azhar.foodapp.ui.home.HomeFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFoodFragment: SearchFoodFragment
    private lateinit var binding: ActivityMenuBinding

    private val tabIcons = intArrayOf(
            R.drawable.ic_food,
            R.drawable.ic_search

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        setupViewPager()
        setupTabIcons()

    }

    private fun setupViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager)

        binding.viewPager.offscreenPageLimit = 2
        binding.tabs.setupWithViewPager(binding.viewPager)


        homeFragment = HomeFragment()
        searchFoodFragment = SearchFoodFragment()

        adapter.addFragment(homeFragment, "Food")
        adapter.addFragment(searchFoodFragment, "Search")
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val fragment =
                    adapter.instantiateItem(binding.viewPager, position)
                if (fragment is LoadDataView) {
                    fragment.loadDataSlider()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setupTabIcons() {
        binding.tabs.getTabAt(0)?.setIcon(tabIcons[0])
        binding.tabs.getTabAt(1)?.setIcon(tabIcons[1])


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav_menu -> {
                val uri = Uri.parse("foodapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}