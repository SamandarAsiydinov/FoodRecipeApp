package com.example.foodrecipeapp.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodrecipeapp.models.FoodRecipe
import com.example.foodrecipeapp.network.NetworkResult
import com.example.foodrecipeapp.repository.FoodRepository
import com.example.foodrecipeapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FoodRepository,
    private val app: Application
) : AndroidViewModel(app) {

    val recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()

        if (Constants.isNetworkAvailable(app)) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Not Found")
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Available")
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                NetworkResult.Error("API Key limited")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                NetworkResult.Error("Recipes Not Found")
            }
            response.isSuccessful -> {
                NetworkResult.Success(response.body()!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}
