package com.example.androidpractice.services.remote.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidpractice.IRemoteServiceInterface
import com.example.androidpractice.databinding.FragmentRemoteService2Binding
import com.example.androidpractice.services.remote.businesslogic.RemoteService
import com.example.androidpractice.ObjectToBeRetrievedParcellable
import com.example.androidpractice.services.remote.viewmodels.RemoteServiceFragmentViewModel
import com.example.androidpractice.services.utils.ToastUtils

class RemoteServiceFragment : Fragment() {
    private val viewModel: RemoteServiceFragmentViewModel by viewModels()
    private var serviceInterface: IRemoteServiceInterface? = null
    private lateinit var binding: FragmentRemoteService2Binding

    private var connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            serviceInterface = IRemoteServiceInterface.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            serviceInterface = null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemoteService2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun startService() {
        viewModel.startService(requireContext(), activity, connection)
    }

    private fun stopService() {
        viewModel.stopService(requireContext(), activity, connection)
    }

    private fun setupButtons() {
        binding.startServiceButton.setOnClickListener {
            startService()
        }

        binding.stopServiceButton.setOnClickListener {
            stopService()
        }

        binding.getFromService.setOnClickListener {
            viewModel.getFromService(serviceInterface, requireContext())
        }

        binding.passToService.setOnClickListener {
            viewModel.passToService(serviceInterface)
        }
    }
}