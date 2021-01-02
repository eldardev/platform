package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class StoreViewModel<A : Action, VS : ViewState> :
    StateViewModel<VS>() {

    abstract val stateMachine: StateMachine<A, VS>

    private val action = BehaviorSubject.create<A>()

    fun dispatch(action: A) {
        this.action.onNext(action)
    }

    init {
        action.subscribe { action ->
            state.value?.let { state ->
                mutableState.postValue(stateMachine.reduce(state, action))

                stateMachine.sideEffects.forEach {
                    it(Observable.just(action), state).subscribe {
                        this.action.onNext(it)
                    }.disposeLater()
                }
            }
        }.disposeLater()
    }
}