package com.temel.mvi.navigation

import androidx.navigation.fragment.NavHostFragment

abstract class Coordinator{
    var navHostFragment: NavHostFragment? = null

    val navController get() = navHostFragment?.navController

    fun clear() {
        navHostFragment = null
    }
}