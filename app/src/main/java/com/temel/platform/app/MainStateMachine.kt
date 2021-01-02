package com.temel.platform.app

import com.temel.mvi.viewmodel.StateMachine
import com.temel.platform.AppState
import com.temel.platform.app.usecase.GetCatsFactsUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainStateMachine @Inject constructor(private var getCatsFactsUseCase: GetCatsFactsUseCase) :
    StateMachine<MainAction, MainState> {

    @Inject
    lateinit var appState: AppState

    override fun initialiseState() = appState.mainState

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

            is MainAction.FetchFacts -> state
        }
    }

    private fun getFacts(
        actions: Observable<MainAction>,
        state: MainState
    ): Observable<MainAction> =
        actions.ofType(MainAction.FetchFacts::class.java).switchMap {
            getCatsFactsUseCase.invoke(Unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<MainAction> {
                    MainAction.ChangeText(it.toString())
                }
                .toObservable()
                .startWith(MainAction.SetLoading(true))
        }

    override val sideEffects: List<(actions: Observable<MainAction>, MainState) -> Observable<MainAction>>
        get() = listOf(::getFacts)
}