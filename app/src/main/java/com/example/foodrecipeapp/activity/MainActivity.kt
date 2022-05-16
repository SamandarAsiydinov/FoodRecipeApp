package com.example.foodrecipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        navController = findNavController(R.id.navHostFragment)
        val appBar = AppBarConfiguration(
            setOf(
                R.id.recipesFragment,
                R.id.searchFragment,
                R.id.favoriteFragment
            )
        )
        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBar)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}