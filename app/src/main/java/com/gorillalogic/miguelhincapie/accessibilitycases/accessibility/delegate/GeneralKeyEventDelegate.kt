package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate

import android.view.KeyEvent.*
import android.view.View
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.domain.accessibility.BaseKeyEventDelegate
import com.gorillalogic.miguelhincapie.domain.accessibility.createKey

class GeneralKeyEventDelegate : BaseKeyEventDelegate() {

    init {
        keyEventActionMap.let {
            it.put(
                createKey(
                    R.id.accessibility_state,
                    KEYCODE_DPAD_DOWN,
                    ACTION_DOWN
                ),
                this::consumeDownKeyOnTitle
            )
            it.put(
                createKey(
                    R.id.button_turn_on,
                    KEYCODE_DPAD_DOWN,
                    ACTION_DOWN
                ),
                this::consumeDownKeyOnTurnOnButton
            )
            it.put(
                createKey(
                    R.id.button_turn_on,
                    KEYCODE_DPAD_UP,
                    ACTION_DOWN
                ),
                this::consumeUpKeyOnTurnOnButton
            )
            it.put(
                createKey(
                    R.id.button_turn_off,
                    KEYCODE_DPAD_UP,
                    ACTION_DOWN
                ),
                this::consumeUpKeyOnTurnOffButton
            )
        }
    }

    private fun consumeDownKeyOnTitle(currentFocus: View): Boolean {
        currentFocus.nextFocusDownId = R.id.gridRV
        return false
    }

    private fun consumeDownKeyOnTurnOnButton(currentFocus: View): Boolean {
        currentFocus.nextFocusDownId = R.id.button_turn_off
        return false
    }

    private fun consumeUpKeyOnTurnOnButton(currentFocus: View): Boolean {
        currentFocus.nextFocusUpId = R.id.carouselRV
        return false
    }

    private fun consumeUpKeyOnTurnOffButton(currentFocus: View): Boolean {
        currentFocus.nextFocusUpId = R.id.button_turn_on
        return false
    }
}