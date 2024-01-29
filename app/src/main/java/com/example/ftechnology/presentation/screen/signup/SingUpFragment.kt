package com.example.ftechnology.presentation.screen.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentSingUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingUpFragment : Fragment() {

    private lateinit var binding: FragmentSingUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() = with(binding) {
        signUpBtn.setOnClickListener {
            val email = tfEmailText.text.toString()
            val password = tfPasswordText.text.toString()
            val confirmPassword = tfPasswordConfirmText.text.toString()

            viewModel.signUp(
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
            Toast.makeText(requireContext(), "Başarılı", Toast.LENGTH_SHORT)
            val action = SingUpFragmentDirections.signUpToSignIn()
            findNavController().navigate(action)
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.error.collect { error: SignUpError ->
                val stringResourceId = SignUpError.toStringResource(error)
                Snackbar.make(requireView(), stringResourceId, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}