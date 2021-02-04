package com.temel.platform.app.feature.main

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.mvi.viewstate.ViewState
import com.temel.platform.app.feature.navigation.MainCoordinator
import com.temel.platform.app.usecase.GetCatsFactsUseCase
import io.reactivex.Observable
import javax.inject.Inject

data class MainState(
    var text: String = "Initial text",
    var isLoading: Boolean = false,
    var isNavigate: Boolean = false
) : ViewState

class MainViewModel @Inject constructor(
    private val getCatsFactsUseCase: GetCatsFactsUseCase,
    private val mainCoordinator: MainCoordinator
) : StoreViewModel<MainAction,
        MainState>() {

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
                state.apply {
                    isNavigate = true
                }
            }
            MainAction.ToList -> {
                mainCoordinator.openListFragment()
                state
            }
        }
    }

    override val middleWares: List<(actions: Observable<MainAction>, MainState) -> Observable<MainAction>>
        get() = listOf(getCatsFactsUseCase::invoke)

    override fun initState() = MainState()
}