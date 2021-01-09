package com.temel.mvi.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class Coordinator{
    var navController: NavController? = null

    var activity: FragmentActivity? = null

    var navHostFragment: NavHostFragment? = null

    fun clear() {
        navController = null
        activity = null
        navHostFragment = null
    }
}