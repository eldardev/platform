package com.temel.platform.app.feature

import com.temel.mvi.ui.activity.NavigationActivity
import com.temel.platform.R
import com.temel.platform.app.coordinator.MainCoordinator
import javax.inject.Inject

class MainActivity : NavigationActivity<MainCoordinator>() {

    @Inject lateinit var mainCoordinator: MainCoordinator

    override val coordinator: MainCoordinator
    get() = mainCoordinator

    override val layoutId: Int
        get() = R.layout.activity_main

    override val navFragmentId: Int
        get() = R.id.nav_host_fragment
}