package com.temel.platform.app.feature.main

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.platform.app.feature.navigation.MainCoordinator
import com.temel.platform.app.interactor.MainInteractor
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val mainCoordinator: MainCoordinator
) : StoreViewModel<MainAction,
        MainState>() {

    init {
        setState(MainState())
    }

    override fun reduce(state: MainState, action: MainAction): MainState {
        return when (action) {
            is MainAction.ChangeText -> {
                state.apply {
                    text = action.text
                    isLoading = false
                }
            }
            is MainAction.Loading -> {
                state.apply {
                    text = ""
                    isLoading = true
                }
            }

            is MainAction.Loaded -> {
                state.apply {
                    isLoading = false
                }
            }

            is MainAction.FetchFacts -> {
//                mainCoordinator.openListFragment()
                state
            }
        }
    }

    private fun getFacts(
        actions: Observable<MainAction>,
        state: MainState
    ): Observable<MainAction> =
        actions.ofType(MainAction.FetchFacts::class.java)
            .switchMap {
                mainInteractor.getFacts()
                    .map<MainAction> {
                        MainAction.ChangeText(it.toString())
                    }
                    .toObservable()
                    .startWith(MainAction.Loading)
            }

    override val sideEffects: List<(actions: Observable<MainAction>, MainState) -> Observable<MainAction>>
        get() = listOf(::getFacts)
//        get() = listOf()
}