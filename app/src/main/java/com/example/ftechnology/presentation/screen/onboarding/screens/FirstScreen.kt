package com.example.ftechnology.presentation.screen.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.tvNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return binding.root

    }
}