package com.temel.platform.app.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.temel.mvi.ui.fragment.AppDaggerFragment
import com.temel.mvi.ui.fragment.StateFragment
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.platform.R

class ListFragment : StateFragment<ListState>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override val viewModel by viewModels<ListViewModel> {
        viewModelFactory
    }

    override fun onNewState(state: ListState) {

    }

    override fun handleThrowable(throwable: Throwable) {

    }
}