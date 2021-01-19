package com.azhar.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
        val foodId: String,
        val nameFood: String,
        val description: String,
        val price: String,
        val code: String,
        val image: String,
        val isFavorite: Boolean
) : Parcelable