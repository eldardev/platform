package com.temel.mvi.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.temel.mvi.extension.renderViewState
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.StateEvent
import com.temel.mvi.viewmodel.StateViewModel
import com.temel.mvi.viewstate.ViewState

abstract class StateBindingActivity<ViewBindingType: ViewBinding, VS : ViewState> : CommonActivity(), StateEvent<VS> {

    abstract val viewModel: StateViewModel<VS>

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBindingType

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: ViewBindingType
        get() = _binding as ViewBindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater(layoutInflater)
        val view = _binding!!.root

        setContentView(view)
    }


    override fun onStart() {
        super.onStart()

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
}