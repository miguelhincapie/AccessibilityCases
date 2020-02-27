package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.KeyEvent
import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.BaseKeyEventDelegate

class GeneralKeyEventDelegate : BaseKeyEventDelegate {

    override fun processKeyEvent(currentFocus: View, event: KeyEvent): Boolean {
        return when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> onDownKeyPressed(currentFocus)
            KeyEvent.KEYCODE_DPAD_UP -> onUpKeyPressed(currentFocus)
            else -> false
        }
    }

    private fun onDownKeyPressed(currentFocus: View): Boolean {
        return when (currentFocus.id) {
            R.id.title -> consumeDownKeyOnTitle(currentFocus)
            R.id.button1 -> consumeDownKeyOnButton1(currentFocus)
            R.id.button2 -> consumeDownKeyOnButton2(currentFocus)
            else -> false
        }
    }

    private fun onUpKeyPressed(currentFocus: View): Boolean {
        return when (currentFocus.id) {
            R.id.button1 -> consumeUpKeyOnButton1(currentFocus)
            R.id.button2 -> consumeUpKeyOnButton2(currentFocus)
            R.id.button3 -> consumeUpKeyOnButton3(currentFocus)
            else -> false
        }
    }

    //region consumeXxxOnYYYY functions
    private fun consumeDownKeyOnTitle(currentFocus: View): Boolean {
        currentFocus.nextFocusDownId = R.id.gridRV
        return false
    }

    private fun consumeDownKeyOnButton1(currentFocus: View): Boolean {
        currentFocus.nextFocusDownId = R.id.button2
        return false
    }

    private fun consumeDownKeyOnButton2(currentFocus: View): Boolean {
        currentFocus.nextFocusDownId = R.id.button3
        return false
    }

    private fun consumeUpKeyOnButton1(currentFocus: View): Boolean {
        currentFocus.nextFocusUpId = R.id.carouselRV
        return false
    }

    private fun consumeUpKeyOnButton2(currentFocus: View): Boolean {
        currentFocus.nextFocusUpId = R.id.button1
        return false
    }

    private fun consumeUpKeyOnButton3(currentFocus: View): Boolean {
        currentFocus.nextFocusUpId = R.id.button2
        return false
    }
    //endregion
}