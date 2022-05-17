package com.example.foodrecipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp.databinding.RecipeLayoutBinding
import com.example.foodrecipeapp.models.Result

class RecipesAdapter : ListAdapter<Result, RecipesAdapter.RecipesViewHolder>(DiffCallBack()) {

    private class DiffCallBack: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            RecipeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecipesViewHolder(private val binding: RecipeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.apply {

            }
        }
    }
}