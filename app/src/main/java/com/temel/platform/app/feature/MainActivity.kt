package com.temel.platform.app.feature

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.ui.activity.CommonActivity
import com.temel.platform.R
import com.temel.platform.app.coordinator.MainCoordinator
import javax.inject.Inject


class MainActivity : CommonActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        coordinator.navHostFragment = navHostFragment
    }

    @Inject
    lateinit var mainCoordinator: MainCoordinator

    private val coordinator: MainCoordinator get() = mainCoordinator
}