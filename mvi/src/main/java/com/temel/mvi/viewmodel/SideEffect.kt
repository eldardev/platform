package com.temel.mvi.viewmodel

import io.reactivex.Observable

typealias SideEffect<S, A> = (action: A, state: S) -> Observable<out A>
