package com.example.androidpractice.services.on_bind.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.databinding.FragmentOnBindServiceBinding
import com.example.androidpractice.services.on_bind.viewmodels.OnBindServiceModel
import com.example.androidpractice.services.utils.ToastUtils

class OnBindServiceFragment : Fragment() {
    private lateinit var binding: FragmentOnBindServiceBinding
    private val viewModel = OnBindServiceModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBindServiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.startServiceButton.setOnClickListener {
            viewModel.connectToService(requireActivity())
        }

        binding.stopServiceButton.setOnClickListener {
            viewModel.disconnectFromService(requireActivity())
        }

        binding.passToService.setOnClickListener {
            viewModel.passToService()
        }

        binding.getFromService.setOnClickListener {
            val passedObject = viewModel.passFromService()
            ToastUtils.showToast(requireContext(), "Got from service, message: ${passedObject.message}, time: ${passedObject.timeCreated}")
        }
    }
}