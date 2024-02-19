package com.example.ftechnology.domain.repository

import com.example.ftechnology.data.source.remote.dto.ProductDto
import retrofit2.Response

interface ProductRepository {

    suspend fun getAllProduct(): Response<List<ProductDto>>
}