package com.example.productexploreapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.productexploreapp.R
import com.example.productexploreapp.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    // Inflate the fragment layout and initialize necessary components
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        setupGetStartedButton()

        return binding.root
    }

    // Function to set up the "Get Started" button
    private fun setupGetStartedButton() {
        // Set listener for the "Get Started" button
        binding.startBtn.setOnClickListener {
            // Navigate to the ProductsScreenFragment when the button is clicked
            findNavController().navigate(R.id.action_splashScreenFragment_to_productsScreenFragment)
        }
    }
}
