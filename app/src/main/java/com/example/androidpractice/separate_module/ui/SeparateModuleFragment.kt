package com.example.androidpractice.separate_module.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.databinding.FragmentSeparateModuleBinding
import com.example.examplelibrary.generateHelloWorldMessage


class SeparateModuleFragment : Fragment() {
    private lateinit var binding: FragmentSeparateModuleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeparateModuleBinding.inflate(layoutInflater)
        binding.textView.text = generateHelloWorldMessage()
        return binding.root
    }
}