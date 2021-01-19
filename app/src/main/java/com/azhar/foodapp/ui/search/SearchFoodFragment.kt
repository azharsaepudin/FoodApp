package com.azhar.foodapp.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.core.ui.FoodAdapter
import com.azhar.foodapp.R
import com.azhar.foodapp.databinding.FragmentSearchFoodBinding
import com.azhar.foodapp.ui.detail.DetailFoodActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFoodFragment : Fragment() {


    private val searchFoodViewModel: SearchFoodViewModel by viewModel()

    private var bindingSearch: FragmentSearchFoodBinding? = null
    private val binding get() = bindingSearch!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindingSearch = FragmentSearchFoodBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            val foodAdapter = FoodAdapter()

            foodAdapter.onItemClick = {
                val intent = Intent(activity, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.DETAIL_DATA, it)
                startActivity(intent)
            }

            binding.brnCari.setOnClickListener {

                val keySearch = binding.edtSearch.text

                if (keySearch.isNotEmpty()) {

                    searchFoodViewModel.getResultRearch("%" + keySearch + "%")
                    searchFoodViewModel.foodResult.observe(viewLifecycleOwner, {
                        foodAdapter.setData(it)

                        if (it.isNotEmpty()) {
                            binding.tvNotFound.visibility = View.GONE
                        } else {
                            binding.tvNotFound.visibility = View.VISIBLE
                        }
                    })


                } else {
                    Toast.makeText(activity, R.string.search_empty, Toast.LENGTH_SHORT).show()
                }
            }

            with(binding.rvFoodSearch) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }

        }


    }
}