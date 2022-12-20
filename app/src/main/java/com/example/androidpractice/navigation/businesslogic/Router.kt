package com.example.androidpractice.navigation.businesslogic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidpractice.custom_view.toucher_view.ui.CustomViewToucherFragment
import com.example.androidpractice.custom_view.ui.CustomViewFragment
import com.example.androidpractice.custom_view.watch_view.ui.CustomViewWatchFragment
import com.example.androidpractice.data_sharing.ui.SharingFragment
import com.example.androidpractice.databinding.ui.DataBindingFragment
import com.example.androidpractice.flavors.ui.FlavorsFragment
import com.example.androidpractice.nav_component.ui.NavComponentFragment
import com.example.androidpractice.navigation.ui.HomeFragment
import com.example.androidpractice.ndk.ui.NdkFragment
import com.example.androidpractice.notification.ui.NotificationFragment
import com.example.androidpractice.retrofit.ui.RetrofitFragment
import com.example.androidpractice.room.ui.RoomFragment
import com.example.androidpractice.sd_card.ui.SdCardFragment
import com.example.androidpractice.separate_module.ui.SeparateModuleFragment
import com.example.androidpractice.services.background.ui.BackgroundServiceFragment
import com.example.androidpractice.services.foreground.ui.ForegroundServiceFragment
import com.example.androidpractice.services.local.ui.LocalServiceFragment
import com.example.androidpractice.services.on_bind.ui.OnBindServiceFragment
import com.example.androidpractice.services.on_start.ui.OnStartServiceFragment
import com.example.androidpractice.services.remote.ui.RemoteServiceFragment
import com.example.androidpractice.services.ui.ServicesFragment
import com.example.androidpractice.vector_views.ui.VectorFragment
import com.example.androidpractice.viewbinding.ui.ViewBindingFragment
import com.example.androidpractice.workmanager.ui.WorkManagerFragment

class Router(
    private val supportFragmentManager: FragmentManager,
    private val containerId: Int,
) {
    fun redirectScreen(screenRoute: Route) {
        val nextFragment = mapRouteToFragment(screenRoute)
        supportFragmentManager.beginTransaction()
            .add(containerId, nextFragment)
            .addToBackStack(screenRoute.name)
            .commit()
    }

    fun popScreen() {
        supportFragmentManager.popBackStack()
    }

    private fun mapRouteToFragment(screenRoute: Route): Fragment {
        return when (screenRoute) {
            Route.Home -> HomeFragment()
            Route.Room -> RoomFragment()
            Route.DataBinding -> DataBindingFragment()
            Route.ViewBinding -> ViewBindingFragment()
            Route.NavigationComponent -> NavComponentFragment()
            Route.Notification -> NotificationFragment()
            Route.Services -> ServicesFragment()
            Route.ForegroundService -> ForegroundServiceFragment()
            Route.BackgroundService -> BackgroundServiceFragment()
            Route.OnBindService -> OnBindServiceFragment()
            Route.OnStartService -> OnStartServiceFragment()
            Route.RemoteService -> RemoteServiceFragment()
            Route.LocalService -> LocalServiceFragment()
            Route.Sharing -> SharingFragment()
            Route.CustomView -> CustomViewFragment()
            Route.WatchCustomView -> CustomViewWatchFragment()
            Route.ToucherCustomView -> CustomViewToucherFragment()
            Route.Ndk -> NdkFragment()
            Route.SeparateModule -> SeparateModuleFragment()
            Route.Retrofit -> RetrofitFragment()
            Route.Flavors -> FlavorsFragment()
            Route.SDCard -> SdCardFragment()
            Route.WorkManager -> WorkManagerFragment()
            Route.VectorView -> VectorFragment()
        }
    }
}