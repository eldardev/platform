package com.temel.mvi.ui.activity

import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.StateEvent
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState

abstract class StateActivity<VS : ViewState> : CommonActivity(), StateEvent<VS> {

    abstract val viewModel: StateViewModel<VS>

    override fun onStart() {
        super.onStart()

        renderViewState(viewModel.state, {
            it?.let {
                onNewState(it)
            }
        })

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)
            }
        })
    }
}