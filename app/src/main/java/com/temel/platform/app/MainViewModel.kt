package com.temel.platform.app

import com.temel.mvi.viewmodel.SideEffect
import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class MainViewModel @Inject constructor(private var getCatsFactsUseCase: GetCatsFactsUseCase) :
    StoreViewModel<MainViewModel.MainAction, MainViewModel.MainState>() {

    sealed class MainAction : Action {
        class ChangeText(val text: String) : MainAction()
        class SetLoading(val isLoading: Boolean) : MainAction()
    }

    @Parcelize
    data class MainState(
        var text: String,
        var isLoading: Boolean
    ) : ViewState

    override fun initViewState(): MainState {
        return MainState("0", false)
    }

    override fun reduce(action: MainAction, state: MainState): MainState {
        return when (action) {
            is MainAction.ChangeText -> {
                state.apply {
                    text = action.text
                    isLoading = false
                }
            }
            is MainAction.SetLoading -> {
                state.apply {
                    isLoading = action.isLoading
                }
            }
        }
    }

    fun getFacts() {
        val effect: SideEffect<MainAction> = {
            test()
        }

        sendSideEffect(effect)

        val sideEffects = listOf<SideEffect<MainAction>>(effect)
    }

    fun test(): Observable<MainAction> {
        return getCatsFactsUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<MainAction> {
                MainAction.ChangeText(it.toString())
            }
            .toObservable()
            .startWith(MainAction.SetLoading(true))
    }
}