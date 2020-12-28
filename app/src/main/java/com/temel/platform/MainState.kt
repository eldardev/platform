package com.temel.platform

import com.temel.mvi.viewstate.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainState(
    var text: String,
    var isLoading: Boolean
) : ViewState