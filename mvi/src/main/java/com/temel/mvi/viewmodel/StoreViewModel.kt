package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

abstract class StoreViewModel<A : Action, VS : ViewState> : ActionViewModel<A, VS>() {

    abstract fun reduce(action: A, state: VS): VS

    init {
        action.subscribe { action ->
            viewState?.let { state ->
                updateState(reduce(action, state))
            }
        }.disposeLater()

        actions.subscribe {
            viewState?.let { state ->
                Observable.fromIterable(it)
                    .subscribe { action ->
                        updateState(reduce(action, state))
                    }.disposeLater()
            }
        }.disposeLater()

        observable.subscribe {
            it.subscribe { action ->
                viewState?.let { state ->
                    updateState(reduce(action, state))
                }
            }.disposeLater()
        }.disposeLater()
    }
}