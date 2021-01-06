package com.temel.mvi.ui.fragment

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class CommonFragment : AppDaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}