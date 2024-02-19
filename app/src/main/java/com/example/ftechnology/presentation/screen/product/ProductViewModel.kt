package com.example.ftechnology.presentation.screen.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.domain.use_case.GetProductsUseCase
import com.example.ftechnology.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel(){

    private val _productsState = MutableStateFlow<Resource<List<ProductDto>>>(Resource.Loading())
    val productsState: StateFlow<Resource<List<ProductDto>>> = _productsState

    val navigateToDetailScreen: MutableSharedFlow<Int> = MutableSharedFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProductsUseCase.execute().collect{resource ->
                _productsState.value = resource
            }
        }
    }

    fun clickProduct(productId: String){
        viewModelScope.launch {
            navigateToDetailScreen.emit(productId.toInt())
        }
    }
}