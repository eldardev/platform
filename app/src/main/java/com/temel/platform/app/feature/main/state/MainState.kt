package com.temel.platform.app.feature.main.state

import com.temel.mvi.viewstate.ViewState
import javax.inject.Inject

class MainState @Inject constructor() : ViewState {
    var text: String = "Initial text"
    var isLoading: Boolean = false
}