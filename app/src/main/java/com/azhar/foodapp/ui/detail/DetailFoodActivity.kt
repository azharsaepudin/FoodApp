package com.azhar.foodapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.azhar.core.domain.model.Food
import com.azhar.foodapp.R
import com.azhar.foodapp.databinding.ActivityDetailFoodBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFoodActivity : AppCompatActivity() {


    companion object {
        const val DETAIL_DATA = "detail_data"
    }

    private val detailFoodViewModel: DetailFoodViewModel by viewModel()

    private lateinit var detailFoodBinding: ActivityDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)
        detailFoodBinding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(detailFoodBinding.root)

        setSupportActionBar(detailFoodBinding.toolbar)

        val detailFood = intent.getParcelableExtra<Food>(DETAIL_DATA)
        showDetailFood(detailFood)

    }

    private fun showDetailFood(detailFood: Food?) {

        detailFood?.let {
            supportActionBar?.title = detailFood.nameFood
            detailFoodBinding.content.tvDetailDescription.text = detailFood.description

            Glide.with(this@DetailFoodActivity)
                    .load(detailFood.image)
                    .into(detailFoodBinding.imageFood)

            var statusFavorite = detailFood.isFavorite
            setStatusFavorite(statusFavorite)

            detailFoodBinding.fab.setOnClickListener {
                statusFavorite = !statusFavorite

                detailFoodViewModel.setFavoriteFood(detailFood, statusFavorite)

                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {

            detailFoodBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            detailFoodBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

}