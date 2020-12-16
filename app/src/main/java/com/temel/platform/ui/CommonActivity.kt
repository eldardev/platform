package com.temel.platform.ui

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CommonActivity :
    DaggerAppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}