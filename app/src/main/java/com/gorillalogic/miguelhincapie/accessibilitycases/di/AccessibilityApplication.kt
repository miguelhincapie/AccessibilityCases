package com.gorillalogic.miguelhincapie.accessibilitycases.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import java.lang.ref.WeakReference

class AccessibilityApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().build(WeakReference(this))
    }
}