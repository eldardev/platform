package com.temel.platform.app.feature.list

import android.os.Bundle
import android.os.PersistableBundle
import com.temel.mvi.ui.activity.CommonActivity
import com.temel.platform.R

class ListActivity : CommonActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }
}