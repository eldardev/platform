package com.temel.mvi.ui.activity

import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.extension.renderViewState

abstract class NavigationActivity : CommonActivity() {

    abstract val navHostFragment: Int

    override fun onStart() {
        super.onStart()

        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostFragment) as NavHostFragment
    }
}