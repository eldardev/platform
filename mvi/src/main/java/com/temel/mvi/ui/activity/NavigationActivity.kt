package com.temel.mvi.ui.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class NavigationActivity : CommonActivity() {

    abstract val navHostFragment: Int

    protected lateinit var navController: NavController

    override fun onStart() {
        super.onStart()

        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}