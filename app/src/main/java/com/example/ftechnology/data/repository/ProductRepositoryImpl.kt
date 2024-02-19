package com.example.ftechnology.data.repository

import com.example.ftechnology.data.source.remote.ProductApi
import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.domain.repository.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
): ProductRepository{
    override suspend fun getAllProduct(): Response<List<ProductDto>> {
        return api.getAllProduct()
    }
}