package com.temel.platform.app.feature

import android.os.Bundle
import com.temel.mvi.navigation.CoordinatorHost
import com.temel.platform.R
import com.temel.mvi.ui.activity.NavigationActivity
import com.temel.platform.app.coordinator.MainCoordinator


class MainActivity : NavigationActivity() {

    override val navHostFragment: Int
        get() = R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}