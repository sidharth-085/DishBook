package com.app.dishbook.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.app.dishbook.presentation.activities.CategoryMealsActivity
import com.app.dishbook.presentation.activities.MainActivity
import com.app.dishbook.presentation.adapters.CategoriesAdapter
import com.app.dishbook.databinding.FragmentCategoriesBinding
import com.app.dishbook.domain.model.Category
import com.app.dishbook.presentation.viewModel.HomeViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        observeCategories()

        onCategoriesClick()
    }

    private fun onCategoriesClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun observeCategories() {
         viewModel.observeCategoryLiveData().observe(viewLifecycleOwner, Observer {categories ->
             val categoriesTillNine = categories.take(12) as MutableList<Category>
             categoriesAdapter.setCategoryList(categoriesTillNine as ArrayList<Category>)
         })
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()

        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context,  3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }
}