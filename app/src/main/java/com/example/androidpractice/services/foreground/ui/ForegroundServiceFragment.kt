package com.example.androidpractice.services.foreground.ui

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.androidpractice.databinding.FragmentForegroundServiceBinding
import com.example.androidpractice.services.foreground.businesslogic.ForegroundService
import com.example.androidpractice.services.foreground.businesslogic.ForegroundServiceBroadCastReceiver
import com.example.androidpractice.services.utils.ObjectToBeRetrieved
import com.example.androidpractice.services.utils.TimeUtils
import com.example.androidpractice.services.utils.ToastUtils
import com.example.androidpractice.services.foreground.viewmodels.ForegroundServiceModel

class ForegroundServiceFragment : Fragment() {
    private lateinit var binding: FragmentForegroundServiceBinding
    private val broadcastReceiver = ForegroundServiceBroadCastReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForegroundServiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupBroadCastReceiver()
    }

    override fun onDetach() {
        super.onDetach()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.startServiceButton.setOnClickListener {
            val intent = Intent(activity, ForegroundService().javaClass)
            intent.action = ForegroundService.START_SERVICE
            activity?.startService(intent)
        }

        binding.stopServiceButton.setOnClickListener {
            val intent = Intent(activity, ForegroundService().javaClass)
            intent.action = ForegroundService.STOP_VALUE
            activity?.startService(intent)
        }

        binding.passToService.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(activity, ForegroundService().javaClass)
            val currentTime = TimeUtils.getDateTime()
            val objectToBeRetrieved = ObjectToBeRetrieved("ObjectToBeRetrieved", currentTime)

            bundle.putSerializable(ForegroundServiceModel.retrieveObjectKey, objectToBeRetrieved)

            intent.action = ForegroundService.RETRIEVE_VALUE
            intent.putExtras(bundle)
            activity?.startService(intent)
        }

        binding.getFromService.setOnClickListener {
            val intent = Intent(activity, ForegroundService().javaClass)
            intent.action = ForegroundService.PASS_VALUE
            activity?.startService(intent)
        }
    }

    private fun setupBroadCastReceiver() {
        val intentFilter = IntentFilter(ForegroundService.FOREGROUND_PASS_ACTION)
        val broadCastManager = LocalBroadcastManager.getInstance(requireContext())
        broadCastManager.registerReceiver(broadcastReceiver, intentFilter)
    }
}