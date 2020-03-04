package com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility

import android.view.KeyEvent
import android.view.View
import androidx.collection.SparseArrayCompat
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.delegate.CarouselKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.delegate.GeneralKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.delegate.GridKeyEventDelegate
import java.lang.ref.WeakReference

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

    private var keyEventActionMap = SparseArrayCompat<KeyEventAction>()

    init {
        keyEventActionMap.apply {
            putAll(GeneralKeyEventDelegate().keyEventActionMap)
            putAll(GridKeyEventDelegate().keyEventActionMap)
            putAll(CarouselKeyEventDelegate().keyEventActionMap)
        }
    }

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