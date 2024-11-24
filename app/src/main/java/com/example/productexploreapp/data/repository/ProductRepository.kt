package com.example.productexploreapp.data.repository


import com.example.productexploreapp.data.local.ProductDao
import com.example.productexploreapp.data.model.Product
import com.example.productexploreapp.data.remote.ApiService
import com.example.productexploreapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) {
    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    fun getProductById(id: Int): Flow<Product> = productDao.getProductById(id)

    suspend fun fetchProducts(): Flow<NetworkResult<List<Product>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                response.body()?.let { products ->
                    productDao.insertProducts(products)
                    emit(NetworkResult.Success(products))
                }
            } else {
                emit(NetworkResult.Error("Failed to fetch products"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error("Network error: ${e.message}"))
        }
    }

    suspend fun fetchProductById(id: String): Flow<NetworkResult<Product>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = apiService.getProductById(id)
            if (response.isSuccessful) {
                response.body()?.let { product ->
                    productDao.insertProduct(product)
                    emit(NetworkResult.Success(product))
                }
            } else {
                emit(NetworkResult.Error("Failed to fetch product"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error("Network error: ${e.message}"))
        }
    }
}