package com.temel.mvi.ui.fragment

import android.os.Bundle
import android.view.View
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState
import dagger.android.support.DaggerFragment

abstract class StateFragment<VS: ViewState> : DaggerFragment() {

    abstract val viewModel : StateViewModel<VS>

    abstract val state: VS

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)

                viewModel.throwable.value = null
            }
        })

        renderViewState(viewModel.state, {
            it?.let {
                onNewState(it)
            }
        })

        viewModel.state.value = state
    }

    abstract fun onNewState(state: VS)

    abstract fun handleThrowable(throwable: Throwable)
}