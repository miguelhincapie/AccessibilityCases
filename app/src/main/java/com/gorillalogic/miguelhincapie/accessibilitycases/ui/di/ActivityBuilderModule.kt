package com.gorillalogic.miguelhincapie.accessibilitycases.ui.di

import com.gorillalogic.miguelhincapie.accessibilitycases.ui.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [UiModule::class])
    abstract fun contributeMainActivity(): MainActivity
}