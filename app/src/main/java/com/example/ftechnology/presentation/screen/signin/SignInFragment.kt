package com.example.ftechnology.presentation.screen.signin

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
import com.example.ftechnology.databinding.FragmentSignInBinding
import com.example.ftechnology.presentation.screen.signup.SignUpError
import com.example.ftechnology.presentation.screen.signup.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observe()
        viewModel.checkUserInfo()
        goToSignUpFragment()
    }
    private fun goToSignUpFragment() {
        binding.tvRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.signInToSignUp)
        }
    }

    private fun initViews() = with(binding){
        signInBtn.setOnClickListener {

            signInBtn.isEnabled = false
            signInBtn.alpha = 0.3f

            val email = tfEmailText.text.toString()
            val password = tfPasswordText.text.toString()
            viewModel.signIn(
                email,
                password
            )
        }
    }

    private fun observe(){
        lifecycleScope.launch {
            viewModel.error.collect { error: SignInError ->
                val stringResourceId = SignInError.toStringResource(error)
                Snackbar.make(requireView(), stringResourceId, Snackbar.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            viewModel.navigateToSignInScreen.collect {
                findNavController().navigate(R.id.signInToProduct)
            }
        }
    }
}