package com.example.androidpractice.custom_view.toucher_view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.custom_view.toucher_view.viewmodel.ToucherCustomView
import com.example.androidpractice.databinding.FragmentCustomViewToucherBinding


class CustomViewToucherFragment : Fragment() {
    private lateinit var binding: FragmentCustomViewToucherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomViewToucherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.resetButton.setOnClickListener {
            binding.toucherView.resetView()
        }
    }
}