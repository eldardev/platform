package com.temel.platform.app.coordinator

import com.temel.mvi.navigation.Coordinator
import com.temel.platform.R
import com.temel.platform.app.di.AppScope
import javax.inject.Inject

@AppScope
class MainCoordinator @Inject constructor(): Coordinator() {

    fun openListFragment() {

        navController?.navigate(R.id.listFragment)
    }
}