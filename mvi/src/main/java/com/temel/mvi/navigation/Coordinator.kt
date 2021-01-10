package com.temel.mvi.navigation

import androidx.navigation.fragment.NavHostFragment

abstract class Coordinator{
    var navHostFragment: NavHostFragment? = null

    fun clear() {
        navHostFragment = null
    }
}