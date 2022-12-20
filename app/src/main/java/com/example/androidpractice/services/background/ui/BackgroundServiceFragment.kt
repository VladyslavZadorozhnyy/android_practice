package com.example.androidpractice.services.background.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentBackgroundServiceBinding
import com.example.androidpractice.services.background.businesslogic.BackgroundService


class BackgroundServiceFragment : Fragment() {
    lateinit var binding: FragmentBackgroundServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBackgroundServiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.startServiceButton.setOnClickListener {
            val intent = Intent(activity, BackgroundService().javaClass)
            intent.action = BackgroundService.START_SERVICE
            activity?.startService(intent)
        }

        binding.stopServiceButton.setOnClickListener {
            val intent = Intent(activity, BackgroundService().javaClass)
            intent.action = BackgroundService.STOP_VALUE
            activity?.startService(intent)
        }
    }
}