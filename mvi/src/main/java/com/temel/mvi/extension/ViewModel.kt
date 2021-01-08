package com.temel.mvi.extension

import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.temel.mvi.navigation.CoordinatorHost
import com.temel.mvi.ui.fragment.StateFragment
import com.temel.mvi.viewmodel.StateViewModel

@MainThread
inline fun <reified VM : ViewModel> Fragment.navGraphViewModel(
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


//@MainThread
//inline fun <reified VM : ViewModel> StateFragment<*>.hostedViewModel(
//    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
//    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = {
//        val coordinator = (requireActivity() as CoordinatorHost<*>).coordinator
//        coordinator.onCreateViewModelFactory(this)
//    }
//) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)
