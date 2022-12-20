package com.example.androidpractice.retrofit.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentRetrofitBinding
import com.example.androidpractice.retrofit.businesslogic.RetrofitController


class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRetrofitBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupButton()
        return binding.root
    }

    private fun setupButton() {
        val controller = RetrofitController()
        binding.textView.movementMethod = ScrollingMovementMethod()

        binding.invokeCall.setOnClickListener {
            binding.textView.scrollTo(0, 0)
            binding.textView.text = "Call in progress"
            controller.start {
                binding.textView.text = it
            }
        }
    }
}