package com.temel.platform.app.di

import androidx.lifecycle.ViewModel
import com.temel.platform.app.feature.MainActivity
import com.temel.platform.app.feature.main.MainViewModel
import com.temel.mvi.di.ViewModelKey
import com.temel.platform.app.feature.list.ListFragment
import com.temel.platform.app.feature.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewmodel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment
}