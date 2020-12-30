package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class StoreViewModel<A : Action, C : Command, VS : ViewState> :
    StateViewModel<VS>() {
    private val action = BehaviorSubject.create<A>()
    private val command = BehaviorSubject.create<C>()

    fun dispatch(action: A) {
        this.action.onNext(action)
    }

    fun sendCommand(command: C) {
        this.command.onNext(command)
    }

    abstract fun reduce(action: A, state: VS): VS

    abstract fun call(command: C): (VS) -> Observable<A>

    init {
        action.subscribe { action ->
            state.value?.let { state ->
                updateState(reduce(action, state))
            }
        }.disposeLater()

        command.subscribe { c ->
            state.value?.let {
                call(c)(it).subscribe { a ->
                    action.onNext(a)
                }.disposeLater()
            }
        }.disposeLater()
    }
}