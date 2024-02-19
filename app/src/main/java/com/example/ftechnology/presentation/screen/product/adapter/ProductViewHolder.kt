package com.example.ftechnology.presentation.screen.product.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.databinding.ProductItemLayoutBinding
import com.example.ftechnology.util.extension.downloadFromUrl
import com.example.ftechnology.util.extension.placeholderProgressBar

class ProductViewHolder (
    private val binding: ProductItemLayoutBinding,
    private val callback: ProductListAdapter.ProductCallBacks
): RecyclerView.ViewHolder(binding.root){
    fun bind(product: ProductDto)= with(binding){
        val imageUrl = product.url
        productNameTv.text= product.name
        productIv.downloadFromUrl(
            imageUrl,
            placeholderProgressBar(context = itemView.context)
            )
        itemView.setOnClickListener {
            callback.onClickProduct(productId = product.id)
        }
    }
}