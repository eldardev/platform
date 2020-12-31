package com.temel.platform.app

import com.temel.mvi.viewmodel.StateViewModel
import com.temel.platform.app.state.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var mainStateMachine: MainStateMachine
) :
    StateViewModel<MainState>() {

//    init {
//        mainStateMachine.state = state
//    }

    override fun onCleared() {
        super.onCleared()
        mainStateMachine.onCleared()
    }
}