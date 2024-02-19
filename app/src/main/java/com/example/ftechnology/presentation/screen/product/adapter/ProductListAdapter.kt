package com.example.ftechnology.presentation.screen.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ftechnology.data.source.remote.dto.ProductDto
import com.example.ftechnology.databinding.ProductItemLayoutBinding

class ProductListAdapter(
    private val callbacks: ProductCallBacks
) : ListAdapter<ProductDto, ProductViewHolder>(ProductDiffCallback()) {


    interface ProductCallBacks {
        fun onClickProduct(productId: String)
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            binding = ProductItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback = callbacks
        )
    }

}