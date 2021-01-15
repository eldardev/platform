package com.temel.mvi.usecase

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

interface UseCase<A: Action, VS: ViewState> {
    fun invoke(action: Observable<A>, state: VS): Observable<A>
}
