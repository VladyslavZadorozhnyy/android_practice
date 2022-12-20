package com.example.androidpractice.services.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.abstractions.BaseFragment
import com.example.androidpractice.databinding.FragmentServicesBinding
import com.example.androidpractice.navigation.businesslogic.Route


class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServicesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.foregroundServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.ForegroundService)
        }

        binding.backgroundServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.BackgroundService)
        }

        binding.onBindServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.OnBindService)
        }

        binding.onStartServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.OnStartService)
        }

        binding.remoteServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.RemoteService)
        }

        binding.localServiceButton.setOnClickListener {
            (activity as BaseFragment.Routable).redirectToScreen(Route.LocalService)
        }
    }
}