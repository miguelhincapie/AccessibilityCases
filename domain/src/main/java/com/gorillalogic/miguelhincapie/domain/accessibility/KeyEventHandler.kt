package com.gorillalogic.miguelhincapie.domain.accessibility

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
import android.view.KeyEvent
import android.view.View
import androidx.collection.SparseArrayCompat
import java.lang.ref.WeakReference
import javax.inject.Inject

const val KEYCODE_CHANGE_ACCESSIBILITY_STATE = KeyEvent.KEYCODE_S

class KeyEventHandler @Inject constructor() {

    /**
     * Contains all possible accessibility actions available for the current Activity.
     */
    private var keyEventActionMap = SparseArrayCompat<KeyEventAction>()

    fun addKeyEventDelegate(keyEventDelegateImpl: BaseKeyEventDelegate) {
        keyEventActionMap.putAll(keyEventDelegateImpl.keyEventActionMap)
    }

    /**
     * If an external keyboard or accessibility device is being used, probably there is a key
     * associated to turn ON/OFF TalkBack service.
     * @return <code>true</code> if the key to turn ON/OFF TalkBack was pressed, <code>false</code>
     * in other case.
     */
    fun switchAccessibilityKeyPressed(event: KeyEvent) =
        KEYCODE_CHANGE_ACCESSIBILITY_STATE == event.keyCode && KeyEvent.ACTION_DOWN == event.action

    fun handleEvent(event: KeyEvent, viewWR: WeakReference<View>): Boolean? {
        return viewWR.get()?.let {
            with(createKey(it.id, event.keyCode, event.action)) {
                keyEventActionMap.get(this)?.invoke(it)
            }
        }
    }
}

/**
 * Allows create a unique [Int] value associated to elements given in parameters.
 *
 * Note: Prime numbers are chosen to best distribute data among hash buckets, so that's why we are using
 * 31 as value.
 */
fun createKey(focusedViewId: Int, keyCode: Int, action: Int = KeyEvent.ACTION_DOWN): Int {
    var result = focusedViewId
    result = 31 * result + keyCode.hashCode()
    result = 31 * result + action.hashCode()
    return result
}