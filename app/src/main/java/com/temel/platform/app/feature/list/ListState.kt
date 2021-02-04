package com.temel.platform.app.feature.list

import com.temel.mvi.viewstate.ViewState

data class ListState(
    var items: List<String> = listOf<String>()
) : ViewState{
}