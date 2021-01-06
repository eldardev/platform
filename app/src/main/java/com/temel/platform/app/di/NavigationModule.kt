package com.temel.platform.app.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.temel.platform.R
import com.temel.platform.app.feature.MainActivity
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @AppScope
    @Provides
    fun navController(mainActivity: MainActivity): NavController {
        val navHostFragment =
            mainActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}