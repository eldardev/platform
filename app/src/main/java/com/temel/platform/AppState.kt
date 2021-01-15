package com.temel.platform

import com.temel.platform.app.feature.main.MainState
import javax.inject.Inject

data class AppState @Inject constructor (
    var mainState: MainState
    )