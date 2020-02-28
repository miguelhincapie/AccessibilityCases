package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.KeyEvent.*
import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.BaseKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.createKey

class GeneralKeyEventDelegate : BaseKeyEventDelegate() {

    init {
        keyEventActionMap.let {
            it.put(
                createKey(R.id.accessibility_state, KEYCODE_DPAD_DOWN, ACTION_DOWN),
                this::consumeDownKeyOnTitle
            )
            it.put(
                createKey(R.id.button1, KEYCODE_DPAD_DOWN, ACTION_DOWN),
                this::consumeDownKeyOnButton1
            )
            it.put(
                createKey(R.id.button2, KEYCODE_DPAD_DOWN, ACTION_DOWN),
                this::consumeDownKeyOnButton2
            )
            it.put(
                createKey(R.id.button1, KEYCODE_DPAD_UP, ACTION_DOWN),
                this::consumeUpKeyOnButton1
            )
            it.put(
                createKey(R.id.button2, KEYCODE_DPAD_UP, ACTION_DOWN),
                this::consumeUpKeyOnButton2
            )
            it.put(
                createKey(R.id.button3, KEYCODE_DPAD_UP, ACTION_DOWN),
                this::consumeUpKeyOnButton3
            )
        }
    }

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
}