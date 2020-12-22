package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Intents
import com.temel.mvi.viewstate.ViewState
import io.reactivex.subjects.BehaviorSubject

abstract class IntentsViewModel<I : Intents, VS: ViewState> : StateViewModel<VS>() {
    internal val intents = BehaviorSubject.create<I>()

    fun sendIntent(intent: I) {
        intents.onNext(intent)
    }
}