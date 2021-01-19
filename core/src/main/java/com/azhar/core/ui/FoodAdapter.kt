package com.azhar.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.core.R
import com.azhar.core.databinding.ItemFoodBinding
import com.azhar.core.domain.model.Food
import com.bumptech.glide.Glide
import java.util.ArrayList

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private var listData = ArrayList<Food>()
    var onItemClick: ((Food) -> Unit)? = null

    fun setData(newListData: List<Food>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false))


    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFoodBinding.bind(itemView)

        fun bind(data: Food) {

            with(binding) {
                Glide.with(itemView.context)
                        .load(data.image)
                        .into(imageView)

                tvCode.text = data.code
                foodName.text = data.nameFood
                description.text = data.description
                price.text = data.price
            }

        }


        init {

            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}