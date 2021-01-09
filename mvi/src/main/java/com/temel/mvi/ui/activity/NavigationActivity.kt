package com.temel.mvi.ui.activity

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.navigation.Coordinator

abstract class NavigationActivity : CommonActivity() {

    abstract val coordinator: Coordinator

    abstract val navHostId: Int

    override fun onStart() {
        super.onStart()

        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostId) as NavHostFragment

        coordinator.navController = findNavController(navHostId)
        coordinator.activity = this

        val host = supportFragmentManager.findFragmentById(navHostId) as NavHostFragment
        coordinator.navHostFragment = host
    }

    override fun onDestroy() {
        super.onDestroy()

        coordinator.clear()
    }
}