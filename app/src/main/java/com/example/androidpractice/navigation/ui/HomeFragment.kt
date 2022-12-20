package com.example.androidpractice.navigation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.androidpractice.R
import com.example.androidpractice.abstractions.BaseFragment
import com.example.androidpractice.databinding.FragmentHomeBinding
import com.example.androidpractice.navigation.businesslogic.Route


class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        setupInfoButton()
        setupFunctionButtons()
    }

    private fun setupInfoButton() {
        val toastHint = "This is Home fragment, it lists available functionality to try"

        binding.infoButton.setOnClickListener {
            Toast.makeText(context, toastHint, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupFunctionButtons() {
        binding.roomButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Room)
        }

        binding.databindingButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.DataBinding)
        }

        binding.viewbindingButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.ViewBinding)
        }

        binding.navComponentButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.NavigationComponent)
        }

        binding.notificationButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Notification)
        }

        binding.servicesButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Services)
        }

        binding.sharingButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Sharing)
        }

        binding.customViewButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.CustomView)
        }

        binding.ndkViewButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Ndk)
        }

        binding.separateModuleButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.SeparateModule)
        }

        binding.retrofitButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Retrofit)
        }

        binding.flavorsButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.Flavors)
        }

        binding.sdButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.SDCard)
        }

        binding.workManagerButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.WorkManager)
        }

        binding.vectorViewButton.setOnClickListener {
            (activity as Routable).redirectToScreen(Route.VectorView)
        }
    }
}