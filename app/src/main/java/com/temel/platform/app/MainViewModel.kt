package com.temel.platform.app

import com.temel.mvi.viewmodel.StoreViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(override val stateMachine: MainStateMachine) :
    StoreViewModel<MainAction,
            MainState>() {

    init {
        mutableState.postValue(stateMachine.initState)
    }
}