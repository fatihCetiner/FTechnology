package com.example.ftechnology.data.source.remote

import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductApi {

    @GET(END_POINT)
    suspend fun getAllProduct(): Response<List<ProductDto>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<ProductDto>
}