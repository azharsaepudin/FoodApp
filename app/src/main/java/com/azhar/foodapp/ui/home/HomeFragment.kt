package com.azhar.foodapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.core.data.Resource
import com.azhar.core.ui.FoodAdapter
import com.azhar.foodapp.databinding.FragmentHomeBinding
import com.azhar.foodapp.ui.detail.DetailFoodActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var bindinghome: FragmentHomeBinding? = null
    private val binding get() = bindinghome!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        bindinghome = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val foodAdapter = FoodAdapter()
            foodAdapter.onItemClick = {

                val intent = Intent(activity, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.DETAIL_DATA, it)
                startActivity(intent)
            }


            homeViewModel.food.observe(viewLifecycleOwner, { foods ->
                if (foods != null) {

                    when (foods) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            foodAdapter.setData(foods.data)

                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE

                        }
                    }


                }
            })


            with(binding.rvFood) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }

        }
    }

}