package com.temel.mvi.navigation

interface Coordinator{
    fun onEvent(event: CoordinatorEvent)
}