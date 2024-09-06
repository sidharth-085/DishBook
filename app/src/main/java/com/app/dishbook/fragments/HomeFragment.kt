package com.app.dishbook.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dishbook.ContactActivity
import com.app.dishbook.R
import com.app.dishbook.activities.CategoryMealsActivity
import com.app.dishbook.activities.MainActivity
import com.app.dishbook.activities.MealActivity
import com.app.dishbook.adapters.CategoriesAdapter
import com.app.dishbook.adapters.MostPopularAdapter
import com.app.dishbook.databinding.FragmentHomeBinding
import com.app.dishbook.dataclasses.Category
import com.app.dishbook.dataclasses.MealsByCategory
import com.app.dishbook.dataclasses.Meal
import com.app.dishbook.viewModel.HomeViewModel
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.app.dishbook.fragments.idMeal"
        const val MEAL_NAME = "com.app.dishbook.fragments.nameMeal"
        const val MEAL_THUMB = "com.app.dishbook.fragments.thumbMeal"
        const val CATEGORY_NAME = "com.app.dishbook.fragments.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        popularItemsAdapter = MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // random meal card view

        viewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        // Popular meals recycler view

        preparePopularItemsRecyclerView()
        viewModel.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemsClick()

        // categories recycler view

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategories()
        onCategoriesMealClick()

        // search icon click function

        onClickListenersForUIElements()
    }

    private fun onClickListenersForUIElements() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment2)
        }

        binding.contactButton.setOnClickListener {
            val intent = Intent(activity, ContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()

        binding.categoriesRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context, 3, GridLayoutManager.VERTICAL, false
            )

            adapter = categoriesAdapter
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.popularItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.HORIZONTAL, false
            )

            adapter = popularItemsAdapter
        }
    }

    private fun observeRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(this@HomeFragment)
                .load(value.strMealThumb).into(binding.imgRandomMeal)

            this.randomMeal = value
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCardView.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)

            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)

            startActivity(intent)
        }
    }

    private fun observePopularItemsLiveData() {
        viewModel.observePopularMealsLiveData().observe(viewLifecycleOwner) { mealList->
            popularItemsAdapter.setMeals(mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun onPopularItemsClick() {
        popularItemsAdapter.onItemClick = { meal->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeCategories() {
        viewModel.observeCategoryLiveData().observe(viewLifecycleOwner, Observer{ categories->
            val firstNineCategories = categories.take(12)
            categoriesAdapter.setCategoryList(firstNineCategories as ArrayList<Category>)
        })
    }

    private fun onCategoriesMealClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }
}