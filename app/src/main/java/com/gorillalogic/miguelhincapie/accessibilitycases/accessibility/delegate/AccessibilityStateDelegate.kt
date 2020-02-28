package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.BaseKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.KEYCODE_CHANGE_ACCESSIBILITY_STATE
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade

@Suppress("UNUSED_PARAMETER")
class AccessibilityStateDelegate : BaseKeyEventDelegate() {
    init {
        keyEventActionMap.put(KEYCODE_CHANGE_ACCESSIBILITY_STATE, this::changeAccessibilityState)
    }

    private fun changeAccessibilityState(currentFocus: View): Boolean {
        if (TalkBackFacade.isTalkBackEnabled())
            TalkBackFacade.disableTalkBack()
        else
            TalkBackFacade.enableTalkBack()
        return true
    }
}