package com.example.foodrecipeapp.source

import com.example.foodrecipeapp.network.FoodRecipesApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(query: Map<String, String>)
    = foodRecipesApi.getRecipes(query)
}