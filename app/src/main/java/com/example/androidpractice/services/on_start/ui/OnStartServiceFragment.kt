package com.example.androidpractice.services.on_start.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.databinding.FragmentOnStartServiceBinding

class OnStartServiceFragment : Fragment() {
    lateinit var binding: FragmentOnStartServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnStartServiceBinding.inflate(layoutInflater)
        return binding.root
    }
}