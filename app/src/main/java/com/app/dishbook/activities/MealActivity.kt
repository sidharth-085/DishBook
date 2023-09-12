package com.app.dishbook.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.app.dishbook.databinding.ActivityMealBinding
import com.app.dishbook.dataclasses.Meal
import com.app.dishbook.db.MealDatabase
import com.app.dishbook.fragments.HomeFragment.Companion.MEAL_ID
import com.app.dishbook.fragments.HomeFragment.Companion.MEAL_NAME
import com.app.dishbook.fragments.HomeFragment.Companion.MEAL_THUMB
import com.app.dishbook.viewModel.MealViewModel
import com.app.dishbook.viewModel.MealViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealMvvm: MealViewModel
    private lateinit var youtubeLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        mealMvvm = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setMealInformationToViews()

        loadingCase()
        mealMvvm.getMealDetails(mealId)
        observeMealDetailsLiveData()

        onYoutubeImageClick()
        onFavoriteClick()
    }

    private fun onFavoriteClick() {
        binding.floatingButton.setOnClickListener {
            mealToSave.let {
                if (it != null) {
                    mealMvvm.insertMeal(it)
                    Snackbar.make(binding.root, "Meal is saved", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private var mealToSave: Meal ?= null
    private fun observeMealDetailsLiveData() {
        mealMvvm.observeMealDetailsLiveData().observe(this
        ) { value ->
            onResponseCase()
            mealToSave = value
            binding.categoryTv.text = "Category: ${value.strCategory}"
            binding.areaTv.text = "Area: ${value.strArea}"
            binding.instructionsSteps.text = value.strInstructions
            youtubeLink = value.strYoutube.toString()
        }
    }

    private fun setMealInformationToViews() {
        Glide.with(this@MealActivity).load(mealThumb).into(binding.imgMealDetail)

        binding.collapsingToolBar.title = mealName
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(MEAL_ID).toString()
        mealName = intent.getStringExtra(MEAL_NAME).toString()
        mealThumb = intent.getStringExtra(MEAL_THUMB).toString()
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.floatingButton.visibility = View.VISIBLE
        binding.instructionsSteps.visibility = View.VISIBLE
        binding.areaTv.visibility = View.VISIBLE
        binding.categoryTv.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.floatingButton.visibility = View.INVISIBLE
        binding.instructionsSteps.visibility = View.INVISIBLE
        binding.areaTv.visibility = View.INVISIBLE
        binding.categoryTv.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }
}