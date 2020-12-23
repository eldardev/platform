package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.subjects.BehaviorSubject

abstract class ActionViewModel<A : Action, VS: ViewState> : StateViewModel<VS>() {
    internal val action = BehaviorSubject.create<A>()
    internal val sideEffects = BehaviorSubject.create<List<A>>()

    fun sendAction(action: A) {
        this.action.onNext(action)
    }

    fun sendSideEffect(list: List<A>){
        this.sideEffects.onNext(list)
    }
}