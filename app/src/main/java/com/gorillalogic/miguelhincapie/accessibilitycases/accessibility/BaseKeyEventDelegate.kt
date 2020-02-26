package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility

import android.view.KeyEvent
import android.view.View

interface BaseKeyEventDelegate {
    fun processKeyEvent(currentFocus: View, event: KeyEvent): Boolean
}