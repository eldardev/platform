package com.temel.platform.app.di

import com.temel.platform.PlatformApplication
import com.temel.platform.di.PlatformScope
import com.temel.platform.di.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PlatformScope
@Component(modules = [AndroidSupportInjectionModule::class,
    ViewModelBuilderModule::class,
    MainModule::class
])
interface MainComponent : AndroidInjector<PlatformApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: PlatformApplication): Builder
        fun build(): MainComponent
    }
}