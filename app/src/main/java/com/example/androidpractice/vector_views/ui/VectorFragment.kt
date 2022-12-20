package com.example.androidpractice.vector_views.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpractice.R
import com.example.androidpractice.databinding.FragmentVectorBinding


class VectorFragment : Fragment() {
    private lateinit var binding: FragmentVectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentVectorBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
//        binding.nonXmlView.x     // AAADIP remove
    }

    private fun setupButtons() {
        binding.xmlButton.setOnClickListener {
            if (binding.xmlView.visibility == View.VISIBLE) {
                binding.xmlView.visibility = View.INVISIBLE
            } else {
                binding.xmlView.visibility = View.VISIBLE
            }
        }

        binding.nonXmlButton.setOnClickListener {
            if (binding.nonXmlView.visibility == View.VISIBLE) {
                binding.nonXmlView.visibility = View.INVISIBLE
            } else {
//                layoutInflater.inflate(R.drawable.path_view_xml, binding.root)
                binding.nonXmlView.visibility = View.VISIBLE
            }
        }
    }
}