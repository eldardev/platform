package com.temel.platform.app.coordinator

import com.temel.mvi.navigation.Coordinator
import com.temel.mvi.navigation.CoordinatorEvent
import javax.inject.Inject

class MainCoordinator @Inject constructor(): Coordinator {
    override fun onEvent(event: CoordinatorEvent) {
        println()
    }
}