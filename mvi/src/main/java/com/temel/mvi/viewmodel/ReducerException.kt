package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState

class ReducerException(
        state: ViewState,
        action: Action,
        cause: Throwable
) : RuntimeException("Exception was thrown by reducer, state = '$state', action = '$action'", cause)
