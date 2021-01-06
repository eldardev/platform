package com.temel.mvi.ui.fragment

import android.os.Bundle
import android.view.View
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState

abstract class StateFragment<VS: ViewState> : CommonFragment() {

    abstract val viewModel : StateViewModel<VS>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderViewState(viewModel.state, {
            it?.let {
                onNewState(it)
            }
        })
    }

    abstract fun onNewState(state: VS)
}