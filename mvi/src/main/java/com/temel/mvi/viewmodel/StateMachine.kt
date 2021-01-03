package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

interface StateMachine<A : Action, VS : ViewState>
    : Reducer<A, VS> {

    val sideEffects: List<(actions: Observable<A>, VS) -> Observable<A>>

    val initState: VS
}