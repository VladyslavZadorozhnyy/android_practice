package com.example.androidpractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.room.Room
import com.example.androidpractice.abstractions.BaseFragment
import com.example.androidpractice.databinding.ActivityMainBinding
import com.example.androidpractice.databinding.FragmentHomeBinding
import com.example.androidpractice.navigation.businesslogic.Route
import com.example.androidpractice.navigation.businesslogic.Router
import com.example.androidpractice.navigation.ui.HomeFragment
import com.example.androidpractice.room.businesslogic.AppDatabase

class MainActivity : AppCompatActivity(), BaseFragment.Routable {
    // Create in DI, and keep it there, but not in 'router' variable. Try injecting instead of creating
    var router: Router = Router(supportFragmentManager, R.id.fragment_screen)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.redirectScreen(Route.Home)

        setContentView(R.layout.activity_main)
    }

    override fun redirectToScreen(route: Route) {
        router.redirectScreen(route)
    }

    override fun redirectBack() {
        router.popScreen()
    }
}