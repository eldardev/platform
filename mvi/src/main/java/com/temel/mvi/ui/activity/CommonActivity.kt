package com.temel.mvi.ui.activity

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CommonActivity : AppDaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}