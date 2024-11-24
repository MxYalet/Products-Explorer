package com.example.productexploreapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productexploreapp.data.model.Product
import com.example.productexploreapp.data.repository.ProductRepository
import com.example.productexploreapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private var _selectedProductId: String? = null
    private val _product = MutableStateFlow<NetworkResult<Product>>(NetworkResult.Loading())
    val product: StateFlow<NetworkResult<Product>> = _product

    private val _products = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val products: StateFlow<NetworkResult<List<Product>>> = _products

    init {
        fetchProducts() // Fetch all products initially
    }

    // Fetch product details dynamically by ID
    fun setProductId(productId: String) {
        _selectedProductId = productId
        fetchProductById(productId)
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                repository.fetchProducts().collectLatest { result ->
                    _products.value = result
                }
            } catch (e: Exception) {
                _products.value = NetworkResult.Error("Error fetching products: ${e.message}")
            }
        }
    }

    fun fetchProductById(productId: String) {
        viewModelScope.launch {
            try {
                repository.fetchProductById(productId).collectLatest { result ->
                    _product.value = result
                }
            } catch (e: Exception) {
                _product.value = NetworkResult.Error("Error fetching product: ${e.message}")
            }
        }
    }

    fun refreshProducts() {
        fetchProducts()
        _selectedProductId?.let { fetchProductById(it) }
    }
}
