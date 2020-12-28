package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.subjects.BehaviorSubject

abstract class ActionViewModel<A : Action, C : Command, VS : ViewState> : StateViewModel<VS>() {
    internal val action = BehaviorSubject.create<A>()
    internal val command = BehaviorSubject.create<C>()

    fun dispatch(action: A) {
        this.action.onNext(action)
    }

    fun sendCommand(command: C) {
        this.command.onNext(command)
    }
}