package com.temel.platform.app.feature.main

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.platform.app.feature.main.state.MainAction
import com.temel.platform.app.feature.main.state.MainState
import com.temel.platform.app.feature.main.state.MainStateMachine
import javax.inject.Inject

class MainViewModel @Inject constructor(
    override val stateMachine: MainStateMachine
) : StoreViewModel<MainAction,
            MainState>() {

    init {
        setState(stateMachine.initState)
    }
}