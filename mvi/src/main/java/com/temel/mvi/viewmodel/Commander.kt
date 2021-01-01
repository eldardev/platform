package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

interface Commander<A : Action, C : Command, VS : ViewState> {

    fun call(command: C): (VS) -> Observable<A>
}