package com.app.dishbook.retrofit

import com.app.dishbook.dataclasses.CategoryList
import com.app.dishbook.dataclasses.MealsByCategory
import com.app.dishbook.dataclasses.MealsByCategoryList
import com.app.dishbook.dataclasses.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealsList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealsList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") id: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("search.php?")
    fun searchMeals(@Query("s") searchQuery: String): Call<MealsList>
}
