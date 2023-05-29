package com.example.foodapp.videoModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.Data.CategoryList
import com.example.foodapp.Data.CategoryMeals
import com.example.foodapp.Data.Meal
import com.example.foodapp.Data.MealList
import com.example.foodapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()


    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<MealList> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() !=null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    Log.d("Home Fragment",t.message.toString())

            }
        })
    }

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularItemsLiveData():LiveData<List<CategoryMeals>>{
        return  popularItemsLiveData
    }

}