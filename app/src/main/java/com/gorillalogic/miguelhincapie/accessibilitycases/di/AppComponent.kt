package com.gorillalogic.miguelhincapie.accessibilitycases.di

import android.app.Application
import android.content.Context
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.di.DomainModule
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.di.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

//@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, UiModule::class, DomainModule::class])
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, DomainModule::class])
interface AppComponent : AndroidInjector<AccessibilityApplication> {
    @Component.Builder
    interface Builder {
        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

//    @Component.Factory
//    interface Factory {
//        fun build(
//            @BindsInstance context: Context
////            , domainComponent: DomainComponent
//        ): AppComponent
//    }
//
//    fun getContext(): Context
}