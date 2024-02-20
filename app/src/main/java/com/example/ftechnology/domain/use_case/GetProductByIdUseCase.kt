package com.example.ftechnology.domain.use_case

import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.domain.repository.ProductRepository
import com.example.ftechnology.util.Resource
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(id: String) = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getProductById(id)
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