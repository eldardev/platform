package com.temel.platform.app.feature.list

import com.temel.mvi.viewmodel.StoreViewModel
import io.reactivex.Observable
import javax.inject.Inject

class ListViewModel @Inject constructor() : StoreViewModel<ListAction, ListState>() {
    override fun initState(): ListState {
        return ListState()
    }

    override val middleWares: List<(actions: Observable<ListAction>, ListState) -> Observable<ListAction>>
        get() = listOf()

    override fun reduce(state: ListState, action: ListAction): ListState {
        return state
    }
}