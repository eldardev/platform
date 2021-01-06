package com.temel.platform.app.coordinator

import androidx.navigation.NavController
import com.temel.platform.R
import javax.inject.Inject

class Router{
    lateinit var navController: NavController

    fun openListFragment(){
        navController.navigate(R.id.listFragment)
    }
}