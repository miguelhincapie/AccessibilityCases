package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility

import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.View
import androidx.collection.SparseArrayCompat
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.AccessibilityStateDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.GridKeyEventDelegate

const val KEYCODE_CHANGE_ACCESSIBILITY_STATE = KeyEvent.KEYCODE_S
/**
 * Prime numbers are chosen to best distribute data among hash buckets, so that's why we are using
 * 31 as value.
 */
fun createKey(focusedViewId: Int, keyCode: Int, action: Int): Int {
    var result = focusedViewId
    result = 31 * result + keyCode.hashCode()
    result = 31 * result + action.hashCode()
    return result
}

class KeyEventHandler {

    private var keyEventActionMap = SparseArrayCompat<BaseKeyEventDelegate>()
    private val gridKeyEventDelegate =
        GridKeyEventDelegate()

    init {
        keyEventActionMap.put(
            KEYCODE_CHANGE_ACCESSIBILITY_STATE,
            AccessibilityStateDelegate()
        )
        keyEventActionMap.put(
            createKey(
                R.id.grid_container,
                KEYCODE_DPAD_DOWN,
                ACTION_DOWN
            ), gridKeyEventDelegate)
    }

    fun handleEvent(view: View, event: KeyEvent): Boolean {
        val key =
            if (KEYCODE_CHANGE_ACCESSIBILITY_STATE == event.keyCode) KEYCODE_CHANGE_ACCESSIBILITY_STATE
            else createKey(
                view.id,
                event.keyCode,
                event.action
            )

        return keyEventActionMap.get(key)
            ?.processKeyEvent(view, event)
            ?: false
    }
}