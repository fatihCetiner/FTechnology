package com.example.ftechnology.presentation.screen.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToSignUpFragment()
    }
    private fun goToSignUpFragment() {
        binding.tvRegisterNow.setOnClickListener {
            val action = SignInFragmentDirections.signInToSignUp()
            findNavController().navigate(action)
        }
    }
}