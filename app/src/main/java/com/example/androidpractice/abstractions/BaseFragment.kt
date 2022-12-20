package com.example.androidpractice.abstractions

import androidx.fragment.app.Fragment
import com.example.androidpractice.navigation.businesslogic.Route

abstract class BaseFragment : Fragment() {
    interface Routable {
        fun redirectToScreen(route: Route)
        fun redirectBack()
    }
}