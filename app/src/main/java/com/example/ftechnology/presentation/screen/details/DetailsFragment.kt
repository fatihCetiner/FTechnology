package com.example.ftechnology.presentation.screen.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentDetailsBinding
import com.example.ftechnology.util.Resource
import com.example.ftechnology.util.extension.downloadFromUrl
import com.example.ftechnology.util.extension.placeholderProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var id: String? = null

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = arguments?.getString("productId") ?: ""
            viewModel.getProductDetail(id!!)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.detailsToProduct)
        }

        observe()
    }

    private fun observe() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productDetailState.collect { resource ->

                when (resource) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val productDetail = resource.data

                        productNameTv.text = productDetail?.name
                        productPriceTv.text = productDetail?.price

                        productImage.downloadFromUrl(
                            productDetail?.url,
                            placeholderProgressBar(requireContext())
                        )
                    }
                    is Resource.Error -> {
                        val errorMessage = resource.message

                    }

                }
            }
        }
    }
}