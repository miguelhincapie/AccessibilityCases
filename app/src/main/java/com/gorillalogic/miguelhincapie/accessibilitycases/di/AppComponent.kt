package com.gorillalogic.miguelhincapie.accessibilitycases.di

import android.content.Context
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.di.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import java.lang.ref.WeakReference

@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<AccessibilityApplication> {
@Component.Factory
    interface Factory {
        fun build(@BindsInstance contextWR: WeakReference<Context>): AppComponent
    }

//    fun getContext(): Context
}