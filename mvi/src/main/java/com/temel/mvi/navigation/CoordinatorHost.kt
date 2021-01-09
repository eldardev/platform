package com.temel.mvi.navigation

interface CoordinatorHost<C : Coordinator> {

    val coordinator: C
}