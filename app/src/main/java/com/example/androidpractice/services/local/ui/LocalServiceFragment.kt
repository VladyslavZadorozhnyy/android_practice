package com.example.androidpractice.services.local.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentLocalServiceBinding


class LocalServiceFragment : Fragment() {
    private lateinit var binding: FragmentLocalServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalServiceBinding.inflate(layoutInflater)
        return binding.root
    }
}