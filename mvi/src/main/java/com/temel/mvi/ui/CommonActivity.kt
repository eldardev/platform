package com.temel.mvi.ui

import androidx.lifecycle.ViewModelProvider
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.CommonViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CommonActivity : DaggerAppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel : CommonViewModel

    override fun onStart() {
        super.onStart()

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)

                viewModel.throwable.value = null
            }
        })
    }

    abstract fun handleThrowable(throwable: Throwable)
}