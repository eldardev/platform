package com.temel.mvi.ui.activity

import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState

abstract class StateActivity<VS : ViewState> : CommonActivity() {

    abstract val viewModel: StateViewModel<VS>

    override fun onStart() {
        super.onStart()

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

        viewModel.selectState(state)
    }

    abstract fun onNewState(state: VS)

    abstract fun handleThrowable(throwable: Throwable)

    abstract val state: VS
}