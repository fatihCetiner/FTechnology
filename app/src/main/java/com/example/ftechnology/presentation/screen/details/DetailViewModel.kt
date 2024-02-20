package com.example.ftechnology.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.domain.use_case.GetProductByIdUseCase
import com.example.ftechnology.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel(){

    private val _productDetailState = MutableStateFlow<Resource<ProductDto>>(Resource.Loading())
    val productDetailState: StateFlow<Resource<ProductDto>> = _productDetailState

    fun getProductById(productId: String) {
        viewModelScope.launch {
            _productDetailState.value = getProductByIdUseCase(productId).first()
        }
    }
}