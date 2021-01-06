package com.temel.platform.app.feature

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.ui.activity.AppDaggerActivity
import com.temel.platform.R
import com.temel.platform.app.coordinator.Router
import javax.inject.Inject

class MainActivity : AppDaggerActivity() {

    @Inject lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        router.navController = navController
    }
}