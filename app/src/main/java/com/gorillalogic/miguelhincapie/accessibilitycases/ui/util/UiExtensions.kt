package com.gorillalogic.miguelhincapie.accessibilitycases.ui.util

import android.view.View
import android.widget.Button
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackState

/**
 * Workaround due we can't just set enable to false because it will lose focusable ability.
 * Check canTakeFocus() function inside View.java file.
 */
fun Button.disable() {
    if (TalkBackState.value == true) {
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
    if (TalkBackState.value == true) {
        isClickable = true
        contentDescription = text
    } else {
        isEnabled = true
    }
}

/**
 * Extension function to set touch values for accessibility
 */
fun List<View>.enableAccessibilityFocus() {
    this.forEach {
        it.setAccessibilityFocus()
    }
}

/**
 * Extension function to remove touch values for accessibility
 */
fun List<View>.disableAccessibilityFocus() {
    this.forEach {
        it.setAccessibilityFocus(false)
    }
}

/**
 * Extension function to set touch/remove values for accessibility depends on params value
 */
fun View.setAccessibilityFocus(value: Boolean = true) {
    this.isFocusable = value
    this.isFocusableInTouchMode = value
}