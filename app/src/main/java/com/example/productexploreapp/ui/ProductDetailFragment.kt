package com.example.productexploreapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.productexploreapp.R
import com.example.productexploreapp.adapter.ProductAdapter
import com.example.productexploreapp.data.model.Product
import com.example.productexploreapp.databinding.FragmentProductDetailBinding
import com.example.productexploreapp.ui.viewModel.ProductListViewModel
import com.example.productexploreapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    // Declare the binding object for this fragment
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductListViewModel by viewModels()
    private val productAdapter = ProductAdapter { product ->
        // Navigate to detail screen
//        findNavController().navigate(
//           // ProductListFragmentDirections.actionProductListToDetail(product.id)
//        )
    }

    // Inflate the fragment layout and initialize necessary components
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)

        // Retrieve productId from arguments
        val productId = arguments?.getString("productId")
            ?: throw IllegalArgumentException("Product ID not found in arguments")


        viewModel.fetchProductById(productId)

        // Set up back arrow navigation
        setUpBackArrowNavigation()

        // Observe the product details
        observeProducts()
        return binding.root
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.product.collectLatest { result ->
                //  binding.swipeToRefresh.isRefreshing = false
                when (result) {
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorText.visibility = View.GONE
                        result.data?.let { displayProductDetails(it) }
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


    // Function to set up back arrow navigation
    private fun setUpBackArrowNavigation() {
        // Set listener for back arrow icon
        binding.backArrow.setOnClickListener {
            // Pop back stack to ProductsScreenFragment
            findNavController().popBackStack(R.id.productsScreenFragment, false)
        }
    }

    private fun displayProductDetails(product: Product) {
        binding.productTitle.text = product.title
        binding.productCategory.text = product.category
        binding.productDesc.text = product.description
        binding.productPrice.text = product.price.toString()

        // Load the product image into the ImageView using Glide
        Glide.with(binding.root.context) // Pass the context from the root view
            .load(product.image)         // The image URL or resource
            .centerCrop()                // Scale the image to fill the view
            .placeholder(R.drawable.image_placeholder) // Optional placeholder
            .error(R.drawable.image_placeholder) // Optional error fallback
            .into(binding.productImage)  // The ImageView from your binding
    }
}
