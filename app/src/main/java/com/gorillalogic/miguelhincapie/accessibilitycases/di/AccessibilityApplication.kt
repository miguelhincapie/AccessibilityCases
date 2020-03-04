package com.gorillalogic.miguelhincapie.accessibilitycases.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AccessibilityApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).context(this).build()
    }
}