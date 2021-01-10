package com.temel.platform.app.feature.main.state

import com.temel.mvi.viewmodel.StateMachine
import com.temel.platform.AppState
import com.temel.platform.app.feature.navigation.MainCoordinator
import com.temel.platform.app.interactor.MainInteractor
import io.reactivex.Observable
import javax.inject.Inject


class MainStateMachine @Inject constructor(
    private var mainInteractor: MainInteractor,
    private val mainCoordinator: MainCoordinator
) :
    StateMachine<MainAction, MainState> {

    @Inject
    lateinit var appState: AppState

    override fun reduce(state: MainState, action: MainAction): MainState {
        return when (action) {
            is MainAction.ChangeText -> {
                state.apply {
                    text = action.text
                    isLoading = false
                }
            }
            is MainAction.SetLoading -> {
                state.apply {
                    text = ""
                    isLoading = action.isLoading
                }
            }

            is MainAction.FetchFacts -> {

                mainCoordinator.openListFragment()
                state
            }
        }
    }

    private fun getFacts(
        actions: Observable<MainAction>,
        state: MainState
    ): Observable<MainAction> =
        actions.ofType(MainAction.FetchFacts::class.java).switchMap {
            mainInteractor.getFacts()
                .map<MainAction> {
                    MainAction.ChangeText(it.toString())
                }
                .toObservable()
                .startWith(MainAction.SetLoading(true))
        }

    override val sideEffects: List<(actions: Observable<MainAction>, MainState) -> Observable<MainAction>>
//        get() = listOf(::getFacts)
        get() = listOf()

    override val initState: MainState
        get() = appState.mainState
}