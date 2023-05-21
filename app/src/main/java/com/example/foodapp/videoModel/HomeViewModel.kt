package com.example.foodapp.videoModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.Data.Meal
import com.example.foodapp.Data.MealList
import com.example.foodapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()

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

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

}