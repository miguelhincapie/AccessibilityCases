package com.gorillalogic.miguelhincapie.accessibilitycases.ui.util

import android.view.View
import java.lang.ref.WeakReference

class WeakRunnable<T>(instance: T?, private val action: (T) -> Unit) : Runnable {
    private val weakReference: WeakReference<T?> = WeakReference(instance)
    override fun run() {
        val instance: T? = weakReference.get()
        if (instance != null) {
            action(instance)
        }
    }
}

fun View.weakPostDelayed(delay: Long = 200, code: (View) -> Unit) {
    postDelayed(WeakRunnable(this) {
        code(it)
    }, delay)
}