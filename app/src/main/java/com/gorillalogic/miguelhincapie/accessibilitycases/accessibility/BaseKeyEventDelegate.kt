package com.gorillalogic.miguelhincapie.accessibilitycases.accessibility

import android.view.View
import androidx.collection.SparseArrayCompat

typealias KeyEventAction = (View) -> Boolean

abstract class BaseKeyEventDelegate {
    val keyEventActionMap = SparseArrayCompat<KeyEventAction>()
}