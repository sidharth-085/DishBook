package com.app.dishbook.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.dishbook.activities.MainActivity
import com.app.dishbook.activities.MealActivity
import com.app.dishbook.adapters.MealsAdapter
import com.app.dishbook.databinding.FragmentFavoritesBinding
import com.app.dishbook.dataclasses.MealsByCategory
import com.app.dishbook.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favoritesAdapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeFavorites()

        val itemTouchHelper = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favoritesAdapter.differ.currentList[position])

                // here we are checking, if after then deletion favorites becomes
                // empty, we have to display No Favorites text

                viewModel.observeFavoritesMealsLiveData().observe(requireActivity(), Observer{ meals ->
                    if (meals.isEmpty()) {
                        binding.tvNoFavorites.visibility = View.VISIBLE
                        binding.numFavorites.visibility = View.INVISIBLE
                        binding.rvFavorites.visibility = View.INVISIBLE
                    }
                })

                Snackbar.make(requireView(),
                    "Meal Deleted", Snackbar.LENGTH_SHORT).show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorites)
    }


    private fun prepareRecyclerView() {
        favoritesAdapter = MealsAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }

    private fun observeFavorites() {
        viewModel.observeFavoritesMealsLiveData().observe(requireActivity(), Observer{ meals ->
            favoritesAdapter.differ.submitList(meals)

            // here I am checking that if meals size is 0, it means our favorites are
            // empty, then make No Favorites visible and others invisible and vice-versa
            // if opposite case

            if (meals.isNotEmpty()) {
                binding.tvNoFavorites.visibility = View.INVISIBLE
            }
            else {
                binding.numFavorites.visibility = View.INVISIBLE
                binding.rvFavorites.visibility = View.INVISIBLE
            }
        })
    }
}