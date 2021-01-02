package com.temel.platform.app

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import kotlinx.android.parcel.Parcelize

sealed class MainAction : Action {
    class ChangeText(val text: String) : MainAction()
    class SetLoading(val isLoading: Boolean) : MainAction()
    object FetchFacts : MainAction()
}

@Parcelize
data class MainState(
    var text: String,
    var isLoading: Boolean
) : ViewState