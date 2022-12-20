package com.example.androidpractice.notification.ui

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentNotificationBinding
import com.example.androidpractice.notification.viewmodels.NotificationViewModel

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private val viewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButtons()
    }

    private fun setupViewModel() {
        viewModel.setContextValue(requireActivity().baseContext)
    }

    private fun setupButtons() {
        binding.immediateNotif.setOnClickListener {
            viewModel.sendImmediateNotification(requireContext())
        }

        binding.delayNotif.setOnClickListener {
            viewModel.sendDelayedNotification(requireContext(), Looper.getMainLooper(), 5)
        }

        binding.progressNotif.setOnClickListener {
            viewModel.sendProgressNotification(requireContext())
        }
    }
}