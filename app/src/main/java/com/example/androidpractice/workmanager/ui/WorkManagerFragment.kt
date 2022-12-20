package com.example.androidpractice.workmanager.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidpractice.databinding.FragmentWorkManagerBinding
import com.example.androidpractice.services.utils.ToastUtils
import com.example.androidpractice.workmanager.businesslogic.WorkManagerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WorkManagerFragment : Fragment() {
    private var buttonLabelNormal = "Start work manager"
    private var buttonLabelProgress = "In progress..."

    private lateinit var binding: FragmentWorkManagerBinding
    private val viewModel: WorkManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWorkManagerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.startButton.setOnClickListener {
            binding.title.text = buttonLabelProgress

            viewModel.startWork(requireContext(), viewLifecycleOwner) { result ->
                binding.title.text = buttonLabelNormal
                ToastUtils.showToast(requireContext(), result ?: "Null")
            }
        }
    }
}