package com.gorillalogic.miguelhincapie.accessibilitycases.ui.util

/**
 * Copyright 2020 [Miguel Hincapie C]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://stackoverflow.com/users/1332549/miguelhincapiec
 * https://github.com/miguelhincapie
 * https://www.linkedin.com/in/miguelhincapie/
 */
import android.view.View
import java.lang.ref.WeakReference

const val DELAY_AFTER_TALK_BACK_GETS_STARTED = 400L

class WeakRunnable<T>(instance: T?, private val action: (T) -> Unit) : Runnable {
    private val weakReference: WeakReference<T?> = WeakReference(instance)
    override fun run() {
        val instance: T? = weakReference.get()
        if (instance != null) {
            action(instance)
        }
    }
}

fun View.weakPostDelayed(delay: Long = DELAY_AFTER_TALK_BACK_GETS_STARTED, code: (View) -> Unit) {
    postDelayed(WeakRunnable(this) {
        code(it)
    }, delay)
}