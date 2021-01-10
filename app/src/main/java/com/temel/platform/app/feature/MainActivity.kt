package com.temel.platform.app.feature

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.ui.activity.CommonActivity
import com.temel.mvi.ui.activity.NavigationActivity
import com.temel.platform.R
import com.temel.platform.app.coordinator.MainCoordinator
import javax.inject.Inject


class MainActivity: NavigationActivity<MainCoordinator>(R.id.nav_host_fragment) {

    @Inject lateinit var mainCoordinator: MainCoordinator

    override val coordinator: MainCoordinator
    get() = mainCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}