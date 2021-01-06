package com.temel.platform.app.feature

import android.os.Bundle
import com.temel.mvi.ui.activity.AppDaggerActivity
import com.temel.platform.R

class MainActivity : AppDaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}