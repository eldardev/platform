package com.temel.platform.app.feature

import android.os.Bundle
import com.temel.platform.R
import com.temel.mvi.ui.activity.NavigationActivity
import com.temel.platform.app.coordinator.MainCoordinator
import javax.inject.Inject


class MainActivity : NavigationActivity() {

    override val navHostId: Int
        get() = R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Inject
    lateinit var mainCoordinator: MainCoordinator

    override val coordinator: MainCoordinator get() = mainCoordinator
}