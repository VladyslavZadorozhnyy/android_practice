package com.example.androidpractice.custom_view.watch_view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentCustomViewWatchBinding
import com.example.androidpractice.databinding.FragmentCustomViewWatchBindingImpl
import com.google.android.material.button.MaterialButton
import java.util.*

class CustomViewWatchFragment : Fragment() {
    private lateinit var binding: FragmentCustomViewWatchBinding
    private lateinit var timer: Timer
    private var redrawingWorking = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomViewWatchBinding.inflate(layoutInflater)
        binding.watchView.smoothAnimation = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        startRedrawing()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopRedrawing()
    }

    private fun startRedrawing() {
        redrawingWorking = true
        timer = Timer()

        val redrawTask = object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    binding.watchView.invalidate()
                }
            }
        }

        val delay = if (binding.watchView.smoothAnimation) {
            1L
        } else {
            1000L
        }

        val period = if (binding.watchView.smoothAnimation) {
            1L
        } else {
            1000L
        }

        timer.schedule(redrawTask, delay, period)
    }

    private fun stopRedrawing() {
        redrawingWorking = false
        timer.cancel()
    }

    private fun setupButtons() {
        binding.resetButton.setOnClickListener {
            if (redrawingWorking) {
                stopRedrawing()
                (it as? MaterialButton)?.text = "Start"
            } else {
                startRedrawing()
                (it as? MaterialButton)?.text = "Stop"
            }
        }
    }
}