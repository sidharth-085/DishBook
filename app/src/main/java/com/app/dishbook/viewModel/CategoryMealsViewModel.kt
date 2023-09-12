package com.app.dishbook.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.dishbook.dataclasses.MealsByCategory
import com.app.dishbook.dataclasses.MealsByCategoryList
import com.app.dishbook.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {
    val mealsLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName)
            .enqueue(object: Callback<MealsByCategoryList> {

            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                if (response.body() != null) {
                    mealsLiveData.value = response.body()!!.meals
                }
                else {
                    return
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("CategoryMealsViewModel", t.message.toString())
            }
        })
    }

    fun observeMealLiveData(): LiveData<List<MealsByCategory>> {
        return mealsLiveData
    }
}