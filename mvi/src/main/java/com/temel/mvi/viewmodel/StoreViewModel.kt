package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class StoreViewModel<A : Action, VS : ViewState> :
    StateViewModel<VS>(), StateMachine<A, VS> {

    private val action = BehaviorSubject.create<A>()

    fun dispatch(action: A) = this.action.onNext(action)

    init {

        this.action.subscribe(
            { action ->
                state.value?.let { currentState ->

                    val newState = reduceNewState(currentState, action)
                    mutableState.postValue(newState)

                    middleWares.forEach {
                        it(Observable.just(action), currentState).subscribe(
                            {
                                this.action.onNext(it)
                            },
                            {
                                this.throwable.postValue(it)
                            }
                        ).disposeLater()
                    }
                }
            },
            {
                throwable.postValue(it)
            }
        ).disposeLater()
    }

    private fun reduceNewState(currentState: VS, action: A): VS {
        return try {
            Timber.d("Reducer reacts on $action. Current State $currentState")
            reduce(currentState, action)
        } catch (error: Throwable) {
            throw ReducerException(state = currentState, action = action, cause = error)
        }
    }

    protected fun setState(state:VS){
        mutableState.postValue(state)
    }
}