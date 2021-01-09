package com.temel.mvi.ui.fragment

import android.os.Bundle
import android.view.View
import com.temel.mvi.extension.observeCoordinatorEvents
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.navigation.Coordinator
import com.temel.mvi.navigation.CoordinatorHost
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

        observeCoordinatorEvents(viewModel.event,{
            it?.let {
                getActivityCoordinator().onEvent(it)
            }
        })
    }

    abstract fun onNewState(state: VS)

    private fun getActivityCoordinator(): Coordinator {
        return (requireActivity() as CoordinatorHost<*>).coordinator
    }
}