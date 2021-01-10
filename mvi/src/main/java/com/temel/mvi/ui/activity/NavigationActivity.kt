package com.temel.mvi.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.fragment.NavHostFragment
import com.temel.mvi.navigation.Coordinator

abstract class NavigationActivity<C : Coordinator> () : AppDaggerActivity() {

    abstract val coordinator: C

    abstract val layoutId: Int
    abstract val navFragmentId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        val navHostFragment =
            supportFragmentManager.findFragmentById(navFragmentId) as NavHostFragment

        coordinator.navHostFragment = navHostFragment
    }

    override fun onDestroy() {
        super.onDestroy()

        coordinator.clear()
    }
}