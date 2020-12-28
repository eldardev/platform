package com.temel.mvi.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.CommonViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class CommonFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel : CommonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)

                viewModel.throwable.value = null
            }
        })
    }

    abstract fun handleThrowable(throwable: Throwable)
}