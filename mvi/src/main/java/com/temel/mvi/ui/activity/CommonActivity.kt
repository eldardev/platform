package com.temel.mvi.ui.activity

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class CommonActivity : AppDaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}