package com.example.ftechnology.presentation.screen.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentProductBinding
import com.example.ftechnology.presentation.screen.product.adapter.ProductListAdapter
import com.example.ftechnology.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel: ProductViewModel by viewModels()

    private val productListAdapter: ProductListAdapter by lazy {
        ProductListAdapter(object : ProductListAdapter.ProductCallBacks {
            override fun onClickProduct(productId: String) {
                viewModel.clickProduct(productId)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        currentUser?.let {
            val userEmail = it.email
            binding.userTv.text = userEmail
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observe()
    }

    private fun initViews() {
        binding.productRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productListAdapter
            setHasFixedSize(true)
        }
    }

    private fun observe() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.productsState.collectLatest { resource ->
                    when(resource){
                        is Resource.Loading -> showLoading()
                        is Resource.Success -> {
                            hideLoading()
                            productListAdapter.submitList(resource.data)
                        }
                        is Resource.Error -> {
                            hideLoading()
                            showError(resource.message ?: "An error occurred")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Not Loading Data !")
            }
        }
        lifecycleScope.launch {
            viewModel.navigateToDetailScreen.collect { productId ->
                findNavController().navigate(R.id.productToDetails)
            }
        }
    }

    private fun showLoading() = with(binding) {
        productPb.visibility = View.VISIBLE
        errorTv.visibility = View.GONE
    }

    private fun hideLoading() = with(binding) {
        productPb.visibility = View.GONE
    }

    private fun showError(errorMessage: String) = with(binding) {
        errorTv.visibility = View.VISIBLE
        errorTv.text = errorMessage
    }

}