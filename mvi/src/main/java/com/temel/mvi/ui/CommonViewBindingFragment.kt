package com.temel.mvi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.CommonViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class CommonViewBindingFragment<ViewBindingType> :
    DaggerFragment() where  ViewBindingType : ViewBinding {

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBindingType

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: ViewBindingType
        get() = _binding as ViewBindingType

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater)

        return _binding!!.root
    }

    abstract val viewModel : CommonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)

                viewModel.throwable.value = null
            }
        })
    }

    abstract fun handleThrowable(throwable: Throwable)
}