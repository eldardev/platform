package com.temel.mvi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.StateEvent
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState

abstract class StateBindingFragment<ViewBindingType: ViewBinding, VS: ViewState> : CommonFragment(), StateEvent<VS> {

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBindingType

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: ViewBindingType
        get() = _binding as ViewBindingType

    abstract val viewModel : StateViewModel<VS>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderViewState(viewModel.state, {
            it?.let {
                onNewState(it)
            }
        })

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}