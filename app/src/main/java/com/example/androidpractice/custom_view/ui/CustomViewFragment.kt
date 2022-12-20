package com.example.androidpractice.custom_view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.abstractions.BaseFragment
import com.example.androidpractice.databinding.FragmentCustomViewBinding
import com.example.androidpractice.navigation.businesslogic.Route


class CustomViewFragment : Fragment() {
    private lateinit var binding: FragmentCustomViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.watchButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.WatchCustomView)
        }

        binding.toucherButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.ToucherCustomView)
        }
    }
}