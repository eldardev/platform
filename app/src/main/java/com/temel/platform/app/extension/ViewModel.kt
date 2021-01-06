package com.temel.platform.app

import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController

@MainThread
public inline fun <reified VM : ViewModel> Fragment.appNavGraphViewModels(
    @IdRes navGraphId: Int,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val backStackEntry by lazy {
        findNavController().getBackStackEntry(navGraphId)
    }

    return createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, {
        factoryProducer?.invoke() ?: backStackEntry.defaultViewModelProviderFactory
    })
}