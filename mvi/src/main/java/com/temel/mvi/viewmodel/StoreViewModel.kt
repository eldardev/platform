package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class StoreViewModel<A : Action, VS : ViewState> :
    StateViewModel<VS>(), StateMachine<A, VS> {

    init {
        setState()
    }

    private val action = BehaviorSubject.create<A>()

    fun dispatch(action: A) = this.action.onNext(action)

    protected fun setState() {
        mutableState.postValue(initState)
    }

    init {
        this.action.subscribe { action ->
            state.value?.let { currentState ->

                val newState = reduceNewState(currentState, action)
                mutableState.postValue(newState)

                sideEffects.forEach {
                    it(Observable.just(action), currentState).subscribe {
                        this.action.onNext(it)
                    }.disposeLater()
                }
            }
        }.disposeLater()
    }

    private fun reduceNewState(currentState: VS, action: A): VS {
        return try {
            Timber.d("Reducer reacts on $action. Current State $currentState")
            reduce(currentState, action)
        } catch (error: Throwable) {
            this.action.onError(error)
            throw ReducerException(state = currentState, action = action, cause = error)
        }
    }
}