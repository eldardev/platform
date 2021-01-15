package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.ViewState

interface StateEvent<VS:ViewState> {
    fun onNewState(state: VS)

    fun handleThrowable(throwable: Throwable)
}