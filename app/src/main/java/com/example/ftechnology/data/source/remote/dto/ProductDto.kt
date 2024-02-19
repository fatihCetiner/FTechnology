package com.example.ftechnology.data.source.remote.dto

data class ProductDto(
    val id: String,
    val name: String,
    val price: String,
    val url: String
){
    var count = 0
}