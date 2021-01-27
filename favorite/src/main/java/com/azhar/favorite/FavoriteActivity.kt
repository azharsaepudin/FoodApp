package com.azhar.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.core.ui.FoodAdapter
import com.azhar.favorite.databinding.ActivityFavoriteBinding
import com.azhar.favorite.di.favoriteModule
import com.azhar.foodapp.ui.detail.DetailFoodActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)

        val foodAdapter = FoodAdapter()

        foodAdapter.onItemClick = {
            val intent = Intent(this, DetailFoodActivity::class.java)
            intent.putExtra(DetailFoodActivity.DETAIL_DATA, it)
            startActivity(intent)
        }

        favoriteViewModel.getFavorite.observe(this, {
            foodAdapter.setData(it)
            binding.layoutEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}