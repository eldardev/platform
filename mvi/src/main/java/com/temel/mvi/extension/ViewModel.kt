package com.temel.mvi.extension


//@MainThread
//inline fun <reified VM : ViewModel> StateFragment<*>.hostedViewModel(
//    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
//    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = {
//        val coordinator = (requireActivity() as CoordinatorHost<*>).coordinator
//        coordinator.onCreateViewModelFactory(this)
//    }
//) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)
