package com.example.productexploreapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productexploreapp.R
import com.example.productexploreapp.adapter.ProductAdapter
import com.example.productexploreapp.databinding.FragmentProductsScreenBinding
import com.example.productexploreapp.ui.viewModel.ProductListViewModel
import com.example.productexploreapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsScreenFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModels()
    private val productAdapter = ProductAdapter { product ->
        // Navigate to ProductDetailFragment with the product ID
        val bundle = Bundle().apply {
            putString("productId", product.id.toString())
        }
        findNavController().navigate(
            R.id.action_productsScreenFragment_to_productDetailFragment,
            bundle
        )
    }
    private lateinit var binding: FragmentProductsScreenBinding

    // Inflate the fragment layout and initialize necessary components
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsScreenBinding.inflate(layoutInflater, container, false)

        // Trigger the network call to fetch products
        viewModel.fetchProducts()
        setupRecyclerView()
        setupSwipeRefresh()
        observeProducts()

        // Set up the back button behavior
        setUpBackButton()


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ProductsScreenFragment.productAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refreshProducts()
        }
    }
    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collectLatest { result ->
              //  binding.swipeToRefresh.isRefreshing = false
                when (result) {
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorText.visibility = View.GONE
                        productAdapter.submitList(result.data)
                        Log.d("Products", "Fetched products: ${result.data}")

                    }
                    is NetworkResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = result.message
                    }
                    is NetworkResult.Loading -> {
                        if (productAdapter.currentList.isEmpty()) {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        binding.errorText.visibility = View.GONE
                    }
                }
            }
        }
    }



    // Function to override the back button behavior
    private fun setUpBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Close the app when the back button is pressed
                    requireActivity().finish()
                }
            })
    }

}
