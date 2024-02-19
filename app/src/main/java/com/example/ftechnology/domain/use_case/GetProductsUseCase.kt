package com.example.ftechnology.domain.use_case


import com.example.ftechnology.domain.repository.ProductRepository
import com.example.ftechnology.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    fun execute() = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getAllProduct()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}