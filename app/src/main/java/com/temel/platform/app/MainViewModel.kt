package com.temel.platform.app

import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState
import com.temel.platform.app.entity.Facts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class MainViewModel @Inject constructor(private var getCatsFactsUseCase :GetCatsFactsUseCase) :
    StoreViewModel<MainViewModel.MainAction, MainViewModel.MainState>() {

    sealed class MainAction : Action {
        class ChangeText (val text: String) : MainAction()
        class SetIsLoading (val isLoading: Boolean) : MainAction()
    }

    @Parcelize
    data class MainState(var text: String,
                         var isLoading: Boolean) : ViewState

    override fun initViewState(): MainState {
        return MainState("0", false)
    }

    override fun reduce(action: MainAction, state: MainState): MainState {
        return when (action) {
            is MainAction.ChangeText -> {
                state.apply {
                    text = action.text
                }
            }
            is MainAction.SetIsLoading -> {
                state.apply {
                    isLoading = action.isLoading
                }
            }
        }
    }

    fun getFacts() {

    }
}