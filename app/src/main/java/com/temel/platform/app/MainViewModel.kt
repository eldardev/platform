package com.temel.platform.app

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.platform.app.state.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor(override val stateMachine: MainStateMachine) :
    StoreViewModel<MainAction,
            MainCommand,
            MainState>() {
}