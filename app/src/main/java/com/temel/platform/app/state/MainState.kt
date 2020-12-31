package com.temel.platform.app.state

import com.temel.mvi.viewstate.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainState(
    var text: String,
    var isLoading: Boolean
) : ViewState