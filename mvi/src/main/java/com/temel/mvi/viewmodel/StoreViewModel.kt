package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class StoreViewModel<A : Action, VS : ViewState> :
    StateViewModel<VS>() {

    abstract val stateMachine: StateMachine<A, VS>

    private val action = BehaviorSubject.create<A>()

    fun dispatch(action: A) = this.action.onNext(action)

    protected fun setState(state: VS) {
        mutableState.postValue(state)
    }

    init {
        this.action.subscribe { action ->
            state.value?.let { currentState ->

                val newState = try {
                    stateMachine.reduce(currentState, action)
                } catch (error: Throwable) {
                    this.action.onError(error)
                    throw ReducerException(state = currentState, action = action, cause = error)
                }
                mutableState.postValue(newState)

                stateMachine.sideEffects.forEach {
                    it(Observable.just(action), currentState).subscribe {
                        this.action.onNext(it)
                    }.disposeLater()
                }
            }
        }.disposeLater()
    }
}