package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility

import android.view.KeyEvent
import android.view.View
import androidx.collection.SparseArrayCompat
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.AccessibilityStateDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.CarouselKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.GeneralKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.GridKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade

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

object KeyEventHandler {

    private var keyEventActionMap = SparseArrayCompat<KeyEventAction>()

    init {
        keyEventActionMap.apply {
            putAll(AccessibilityStateDelegate().keyEventActionMap)
            putAll(GeneralKeyEventDelegate().keyEventActionMap)
            putAll(GridKeyEventDelegate().keyEventActionMap)
            putAll(CarouselKeyEventDelegate().keyEventActionMap)
        }
    }

    fun handleEvent(view: View, event: KeyEvent): Boolean {
        val key =
            if (KEYCODE_CHANGE_ACCESSIBILITY_STATE == event.keyCode) KEYCODE_CHANGE_ACCESSIBILITY_STATE
            else createKey(view.id, event.keyCode, event.action)

        return if (key != KEYCODE_CHANGE_ACCESSIBILITY_STATE && !TalkBackFacade.isTalkBackEnabled())
            false
        else
            keyEventActionMap.get(key)?.invoke(view) ?: false
    }
}