package com.example.ftechnology.presentation.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ftechnology.R
import com.example.ftechnology.databinding.FragmentViewPagerBinding
import com.example.ftechnology.presentation.screen.onboarding.adapter.ViewPagerAdapter
import com.example.ftechnology.presentation.screen.onboarding.screens.FirstScreen
import com.example.ftechnology.presentation.screen.onboarding.screens.SecondScreen
import com.example.ftechnology.presentation.screen.onboarding.screens.ThirdScreen
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        return binding.root
    }
}