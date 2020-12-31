package com.temel.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class StateMachine<A : Action, C : Command, VS : ViewState> {
    lateinit var state: MutableLiveData<VS>

    private val action = BehaviorSubject.create<A>()
    private val command = BehaviorSubject.create<C>()

    private val disposeBag = CompositeDisposable()

    abstract fun reduce(action: A, state: VS): VS

    abstract fun call(command: C): (VS) -> Observable<A>

    fun onCleared() {
        disposeBag.dispose()
    }

    fun dispatch(action: A) {
        this.action.onNext(action)
    }

    fun sendCommand(command: C) {
        this.command.onNext(command)
    }

    private fun Disposable.disposeLater(): Disposable {
        disposeBag.add(this)
        return this
    }

    private fun reduceMiddleware(action: A): VS{
        state.value?.let {
            return reduce(action, it)
        }

        throw Exception("State is null")
    }

    init {
        action.subscribe { action ->
            state.postValue((reduceMiddleware(action)))
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