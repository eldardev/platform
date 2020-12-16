package com.temel.mvi.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
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
}