package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

typealias Reducer<S, A> = (currentState: S, newAction: A) -> S

interface StateMachine<A : Action, C : Command, VS : ViewState> {

    fun reduce(state: VS, action: A, ): VS

    fun call(command: C): (VS) -> Observable<A>
}