package com.temel.platform.app

import com.temel.platform.viewstate.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainState(var text: String) : ViewState