package com.temel.mvi.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.navigation.Coordinator
import com.temel.mvi.navigation.CoordinatorHost

abstract class NavigationActivity<C : Coordinator> : AppDaggerActivity(), CoordinatorHost<C> {

    abstract val layoutId: Int
    abstract val navHostId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostId) as NavHostFragment

        coordinator.navHostFragment = navHostFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(navHostId).navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

        coordinator.clear()
    }
}