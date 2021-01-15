package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

interface StateMachine<A : Action, VS : ViewState>
    : Reducer<A, VS> {

    val middleWares: List<(actions: Observable<A>, VS) -> Observable<A>>
}