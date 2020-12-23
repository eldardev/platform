package com.temel.mvi.viewmodel

import io.reactivex.Observable

typealias SideEffect<A> = () -> Observable<out A>
