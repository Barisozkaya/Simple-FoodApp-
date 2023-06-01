package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.Data.MealsByCategory
import com.example.foodapp.databinding.MealsItemBinding


class CategoryMealsAdapter : RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>(){
   private var mealsList = ArrayList<MealsByCategory>()

     fun setMealsList(mealsList:List<MealsByCategory>){
         this.mealsList = mealsList as ArrayList<MealsByCategory>
         notifyDataSetChanged()
     }
        inner class CategoryMealsViewModel( val binding:MealsItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
        return CategoryMealsViewModel(
            MealsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }
    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = mealsList[position].strMeal
    }


    override fun getItemCount(): Int {
        return mealsList.size
    }
}