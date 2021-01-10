package com.temel.mvi.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.navigation.Coordinator

abstract class NavigationActivity<C : Coordinator> (private val navFragmentId: Int) : AppDaggerActivity() {

    abstract val coordinator: C

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(navFragmentId) as NavHostFragment

        coordinator.navHostFragment = navHostFragment
    }

    override fun onDestroy() {
        super.onDestroy()

        coordinator.clear()
    }
}