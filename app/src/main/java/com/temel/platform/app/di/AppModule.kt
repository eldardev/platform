package com.temel.platform.app.di

import androidx.lifecycle.ViewModel
import com.temel.platform.app.feature.MainActivity
import com.temel.platform.app.feature.main.MainViewModel
import com.temel.mvi.di.ViewModelKey
import com.temel.platform.app.feature.list.ListFragment
import com.temel.platform.app.feature.list.ListViewModel
import com.temel.platform.app.feature.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewmodel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewmodel: ListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment
}