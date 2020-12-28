package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import io.reactivex.Observable

typealias Reducer<T> = (state: T, action : Action) -> T
typealias SideEffect<S, A> = (action: A, state: S) -> Observable<out A>
