package com.example.androidpractice.viewbinding.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentViewBindingBinding
import com.example.androidpractice.viewbinding.viewmodel.ViewbindingViewModel


class ViewBindingFragment : Fragment() {
    lateinit var binding: FragmentViewBindingBinding
    private val viewModel: ViewbindingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBindingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleText.text = viewModel.getTitle()
        setupButtons()
    }

    private fun setupButtons() {
        binding.resetTitleButton.setOnClickListener {
            viewModel.resetTitle()
            binding.titleText.text = viewModel.getTitle()
        }

        binding.shuffleTitleButton.setOnClickListener {
            viewModel.shuffleTitle()
            binding.titleText.text = viewModel.getTitle()
        }
    }
}