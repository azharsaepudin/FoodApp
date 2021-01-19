package com.azhar.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.azhar.foodapp.adapters.ViewPagerAdapter
import com.azhar.foodapp.ui.search.SearchFoodFragment
import com.azhar.foodapp.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFoodFragment: SearchFoodFragment

    private val tabIcons = intArrayOf(
            R.drawable.ic_food,
            R.drawable.ic_search

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.elevation = 0f
        setupViewPager()
        setupTabIcons()

    }

    private fun setupViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager)

        viewPager?.offscreenPageLimit = 2
        tabs?.setupWithViewPager(viewPager)


        homeFragment = HomeFragment()
        searchFoodFragment = SearchFoodFragment()

        adapter.addFragment(homeFragment, "Food")
        adapter.addFragment(searchFoodFragment, "Search")
        viewPager?.adapter = adapter

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val fragment =
                        adapter.instantiateItem(viewPager!!, position)
                if (fragment is LoadDataView) {
                    fragment.loadDataSlider()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setupTabIcons() {
        tabs?.getTabAt(0)?.setIcon(tabIcons[0])
        tabs?.getTabAt(1)?.setIcon(tabIcons[1])


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav_menu -> {
                val i = Intent()
                i.setClassName(BuildConfig.APPLICATION_ID, "com.azhar.favorite.FavoriteActivity")
                startActivity(i)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}