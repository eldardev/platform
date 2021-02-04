package com.temel.platform.app.feature.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.temel.mvi.viewmodel.StoreViewModel
import com.temel.platform.BR
import com.temel.platform.R
import io.reactivex.Observable
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject


class ListViewModel @Inject constructor() : StoreViewModel<ListAction, ListState>() {

    val items: ObservableList<String> = ObservableArrayList()
    val itemBinding = ItemBinding.of<String>(BR.itemString, R.layout.item_list)

    override fun initState(): ListState {
        return ListState()
    }

    override val middleWares: List<(actions: Observable<ListAction>, ListState) -> Observable<ListAction>>
        get() = listOf()

    override fun reduce(state: ListState, action: ListAction): ListState {
        when (action){
            is ListAction.LoadList -> {
                state.apply {
                    items = listOf("A", "B", "C")
                }
            }
        }
        return state
    }
}