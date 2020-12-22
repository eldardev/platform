package com.temel.platform.app

import com.temel.mvi.viewmodel.StoreViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(mainReducer: MainReducer) : StoreViewModel<MainIntent, MainState>(mainReducer) {

    override fun initViewState(): MainState {
        return MainState("", false)
    }
}