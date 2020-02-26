package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.KeyEvent
import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.BaseKeyEventDelegate

class CarouselKeyEventDelegate :
    BaseKeyEventDelegate {
    override fun processKeyEvent(currentFocus: View, event: KeyEvent): Boolean {
        return when(event.keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> onDownKeyPressed(currentFocus)
            KeyEvent.KEYCODE_DPAD_UP -> onUpKeyPressed(currentFocus)
            KeyEvent.KEYCODE_ENTER -> onEnterKeyPressed(currentFocus)
            else -> false
        }
    }

    private fun onDownKeyPressed(currentFocus: View) : Boolean {
        return false
    }

    private fun onUpKeyPressed(currentFocus: View) : Boolean {
        return false
    }

    private fun onEnterKeyPressed(currentFocus: View) : Boolean {
        return false
    }
}