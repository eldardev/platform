package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class ActionViewModel<A : Action, VS : ViewState> : StateViewModel<VS>() {
    internal val action = BehaviorSubject.create<A>()
    internal val actions = BehaviorSubject.create<List<A>>()
    internal val observable = BehaviorSubject.create<Observable<A>>()

    fun sendAction(action: A) {
        this.action.onNext(action)
    }

    fun sendAction(list: List<A>) {
        this.actions.onNext(list)
    }

    fun sendAction(observable: Observable<A>){
        this.observable.onNext(observable)
    }
}