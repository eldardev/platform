package com.temel.platform.app.coordinator

import com.temel.mvi.navigation.Coordinator
import com.temel.platform.R
import javax.inject.Inject

class MainCoordinator @Inject constructor(): Coordinator() {

    fun openListFragment() {
        println()
//        navController?.navigate(R.id.listFragment)
    }
}