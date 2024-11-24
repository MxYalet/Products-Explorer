package com.example.productexploreapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.productexploreapp.databinding.ActivityMainBinding
import com.example.productexploreapp.ui.viewModel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // Declare the binding object for this activity
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductListViewModel by viewModels()

    // Initialize the activity and set up the navigation controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this activity
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set up NavController to manage navigation within the app
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Set the content view to the inflated layout
        setContentView(binding.root)
    }
}