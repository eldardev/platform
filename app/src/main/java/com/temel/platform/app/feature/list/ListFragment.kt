package com.temel.platform.app.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.temel.mvi.ui.fragment.StateFragment
import com.temel.platform.R
import com.temel.platform.databinding.FragmentListBinding

class ListFragment : StateFragment<ListState>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override val viewModel by viewModels<ListViewModel> {
        viewModelFactory
    }

    override fun onNewState(state: ListState) {
        viewModel.items.addAll(state.items)
    }

    override fun handleThrowable(throwable: Throwable) {

    }

    override fun onResume() {
        super.onResume()

        viewModel.dispatch(ListAction.LoadList)
    }
}