package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.subjects.BehaviorSubject

abstract class StoreViewModel<A : Action, C : Command, VS : ViewState> :
    StateViewModel<VS>() {

    private val action = BehaviorSubject.create<A>()
    private val command = BehaviorSubject.create<C>()

    abstract val stateMachine: StateMachine<A, C, VS>

    fun dispatch(action: A) {
        this.action.onNext(action)
    }

    fun sendCommand(command: C) {
        this.command.onNext(command)
    }

    init {
        action.subscribe { action ->
            state.value?.let { state ->
                mutableState.postValue(stateMachine.reduce(action, state))
            }
        }.disposeLater()

        command.subscribe { c ->
            state.value?.let {
                stateMachine.call(c)(it).subscribe { a ->
                    action.onNext(a)
                }.disposeLater()
            }
        }.disposeLater()
    }
}