package com.gorillalogic.miguelhincapie.accessibilitycases.ui

import android.widget.Button
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade

/**
 * Workaround due we can't just set enable to false because it will lose focusable ability.
 * Check canTakeFocus() function inside View.java file.
 */
fun Button.disable() {
    if (TalkBackFacade.isTalkBackEnabled()) {
        isClickable = false
        // contentDescription = String.format(context.getString(R.string.button_disabled_accessibility), text)
    } else {
        isEnabled = false
    }

}

/**
 * Workaround due we can't just set enable to false because it will lose focusable ability.
 * Check canTakeFocus() function inside View.java file.
 */
fun Button.enable() {
    if (TalkBackFacade.isTalkBackEnabled()) {
        isClickable = true
        contentDescription = text
    } else {
        isEnabled = true
    }
}


fun Int.toBoolean() = this == 1
fun Boolean.intValue() = if (this) 1 else 0