package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

abstract class StoreViewModel<A : Action, C : Command, VS : ViewState> :
    ActionViewModel<A, C, VS>() {

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