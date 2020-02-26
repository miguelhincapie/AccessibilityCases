package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.KeyEvent
import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.BaseKeyEventDelegate

class AccessibilityStateDelegate :
    BaseKeyEventDelegate {
    override fun processKeyEvent(currentFocus: View, event: KeyEvent): Boolean {
        if (TalkBackFacade.isTalkBackEnabled())
            TalkBackFacade.disableTalkBack()
        else
            TalkBackFacade.enableTalkBack()
        return true
    }
}