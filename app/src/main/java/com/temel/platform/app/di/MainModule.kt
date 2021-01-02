package com.temel.platform.app.di

import androidx.lifecycle.ViewModel
import com.temel.platform.app.feature.main.MainActivity
import com.temel.platform.app.feature.main.MainViewModel
import com.temel.mvi.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewmodel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}