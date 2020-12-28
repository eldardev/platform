package com.temel.mvi.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.temel.mvi.extension.throwable
import com.temel.mvi.viewmodel.CommonViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CommonViewBindingActivity<ViewBindingType> :
    DaggerAppCompatActivity()
        where ViewBindingType : ViewBinding{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

    abstract val viewModel : CommonViewModel

    override fun onStart() {
        super.onStart()

        throwable(viewModel.throwable, {
            it?.let {
                handleThrowable(it)

                viewModel.throwable.value = null
            }
        })
    }

    abstract fun handleThrowable(throwable: Throwable)
}