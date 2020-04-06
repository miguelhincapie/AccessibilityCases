package com.gorillalogic.miguelhincapie.accessibilitycases.ui.util

/**
 * Copyright 2020 [Miguel Hincapie C]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://stackoverflow.com/users/1332549/miguelhincapiec
 * https://github.com/miguelhincapie
 * https://www.linkedin.com/in/miguelhincapie/
 */
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityEventCompat
import androidx.core.view.doOnNextLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.viewmodel.TalkBackState

//region enable/disable button
/**
 * Extension function to disable a Button.
 * Workaround due we can't just set enable to false because it will lose focusable ability.
 * Check canTakeFocus() function inside View.java file.
 */
fun Button.disable(@DrawableRes drawableForAccessibility: Int) {
    if (TalkBackState.value == true) {
        isEnabled = true
        isClickable = false
        contentDescription =
            String.format(context.getString(R.string.button_disabled_accessibility), text)
        background = ContextCompat.getDrawable(context, drawableForAccessibility)
    } else {
        isEnabled = false
    }
}

/**
 * Extension function to enable a Button.
 * Workaround due we can't just set enable to false because it will lose focusable ability.
 * Check canTakeFocus() function inside View.java file.
 */
fun Button.enable(@DrawableRes drawableForAccessibility: Int) {
    if (TalkBackState.value == true) {
        isEnabled = true
        isClickable = true
        contentDescription = context.getString(R.string.button_accessibility_label, text)
        background = ContextCompat.getDrawable(context, drawableForAccessibility)
    } else {
        isEnabled = true
    }
}
//endregion

//region focus
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
    this.isFocusableInTouchMode = value
    this.isFocusable = value
}
//endregion

//region RecyclerView and Grid ext fun
/**
 * Extension function to be used with elements inside a recyclerView.
 */
fun View.getNextElementPosition(): Int {
    val params = getRecyclerViewLayoutParams(this) as? RecyclerView.LayoutParams
    params?.let {
        return if (it.isItemRemoved) {
            // when item is removed, the position value can be any value.
            RecyclerView.NO_POSITION
        } else it.viewAdapterPosition + 1
    } ?: return RecyclerView.NO_POSITION
}

/**
 * Extension function to be used with elements inside a recyclerView.
 */
fun View.getPreviousElementPosition(): Int {
    val params = getRecyclerViewLayoutParams(this) as? RecyclerView.LayoutParams
    params?.let {
        return if (it.isItemRemoved) {
            // when item is removed, the position value can be any value.
            RecyclerView.NO_POSITION
        } else it.viewAdapterPosition - 1
    } ?: return RecyclerView.NO_POSITION
}

/**
 * Function to be used with elements inside a recyclerView.
 * Sometimes the focused element inside the recyclerView is deep in view graph (like in Catalog
 * Product Screen), so we need to go through the graph looking for the right element.
 */
private fun getRecyclerViewLayoutParams(view: View?): ViewGroup.LayoutParams? {
    return view?.layoutParams?.let {
        if (it is RecyclerView.LayoutParams) return it else getRecyclerViewLayoutParams(view.parent as? View)
    } ?: return null
}

/**
 * Extension function to be used with elements inside a recyclerView.
 *
 * @return <code>true</code> if there is a recycler's item in the position given in param,
 * <code>false</code> otherwise.
 */
fun View.isPositionInbound(currentPosition: Int): Boolean {
    return this.findParentRecyclerView()?.adapter?.let {
        currentPosition > RecyclerView.NO_POSITION && currentPosition < it.itemCount
    } ?: false
}

/**
 * Extension function to get the first ancestor of type "RecyclerView" of a View.
 *
 * @return [RecyclerView] or <code>null</code> if couldn't find one.
 */
fun View.findParentRecyclerView(): RecyclerView? {
    if (this is RecyclerView) return this
    return when (parent) {
        null -> null
        is RecyclerView -> parent as RecyclerView
        else -> (parent as View).findParentRecyclerView()
    }
}

/**
 * Extension function to be used within *recyclerView's elements* or in the *recyclerView itself*.
 *
 * Internally [RecyclerView.sendFocusToSiblingElement] ext function is called.
 */
fun View.sendFocusToListItem(nextPosition: Int, offset: Int = LinearLayoutManager.INVALID_OFFSET) {
    (this.findParentRecyclerView() ?: return).apply {
        sendFocusToSiblingElement(
            position = nextPosition,
            focusAction = sendFocusToSiblingElement,
            offset = offset
        )
    }
}

/**
 * Extension function to send focus to the *fist* element in the recyclerView.
 *
 * This can be used within recyclerView's elements or in the recyclerView itself.
 * Internally [RecyclerView.sendFocusToSiblingElement] ext function is called.
 */
fun View.sendFocusToFirstElement(
    focusAction: (childAt: View?, rv: RecyclerView) -> Boolean? = sendFocusToSiblingElement,
    forceScroll: Boolean = false
) {
    (this.findParentRecyclerView() ?: return).apply {
        sendFocusToSiblingElement(0, focusAction, forceScroll)
    }
}

/**
 * Extension function to send focus to the *last* element in the recyclerView.
 *
 * Internally [RecyclerView.sendFocusToSiblingElement] ext function is called.
 */
fun View.sendFocusToLastElement(
    focusAction: (childAt: View?, rv: RecyclerView) -> Boolean? = sendFocusToSiblingElementAtTheEnd,
    forceScroll: Boolean = false
) {
    (this.findParentRecyclerView() ?: return).apply {
        adapter?.let {
            sendFocusToSiblingElement(it.itemCount.minus(1), focusAction, forceScroll)
        }
    }
}

/**
 * Extension function which actually gives the focus to an element inside a recyclerView no matter
 * if the element is visible or not, in last case a scroll process will precede the focus request.
 *
 * @param position position inside the recyclerView which will get the focus.
 * @param focusAction how the focus will be send to the element. Those actions can be
 * @param forceScroll indicates if the scroll should be done always
 * @param offset The distance (in pixels) between the start edge of the item view and
 *               start edge of the RecyclerView.
 * [sendFocusToSiblingElement], [sendFocusToSiblingElementAtTheEnd]
 */
private fun RecyclerView.sendFocusToSiblingElement(
    position: Int,
    focusAction: (childAt: View?, rv: RecyclerView) -> Boolean?,
    forceScroll: Boolean = false,
    offset: Int = LinearLayoutManager.INVALID_OFFSET
) {
    (this.layoutManager as? LinearLayoutManager)?.let {
        if (it.isVisibleItemPosition(position) && !forceScroll) {
            this.findViewHolderForAdapterPosition(position)?.itemView?.sendAccessibilityFocus()
        } else {
            it.scrollToPositionWithOffset(position, offset)
            this.doOnNextLayout {
                val childAt = this.findViewHolderForAdapterPosition(position)?.itemView
                focusAction(childAt, this)
            }
        }
    } ?: Log.e(
        this::class.simpleName,
        "${this.layoutManager} couldn't be cast to LinearLayoutManager"
    )
}

val sendFocusToSiblingElement =
    { childAt: View?, rv: RecyclerView -> childAt?.let { rv.post { it.sendAccessibilityFocus() } } }

val sendFocusToSiblingElementAtTheEnd =
    { childAt: View?, rv: RecyclerView -> childAt?.let { rv.post { it.sendAccessibilityFocus(View.FOCUS_UP) } } }

fun View.sendFocusToRecyclerViewContainer() {
    (this.parent?.parent as? View)?.requestFocus()
}

/**
 * Extension that allows a view to send accessibility events to gain focus and announcement.
 *
 * @param focusDirection to inform the focus is coming from top or bottom.
 */
fun View.sendAccessibilityFocus(focusDirection: Int = View.FOCUS_DOWN) {
    requestFocus(focusDirection)
    this.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
    this.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED)
    this.sendAccessibilityEvent(AccessibilityEventCompat.TYPE_ANNOUNCEMENT)
}

/**
 * Extension function use to see if an item is visible within a grid layout
 */
fun LinearLayoutManager.isVisibleItemPosition(position: Int): Boolean =
    (position >= this.findFirstCompletelyVisibleItemPosition()
            && position <= this.findLastCompletelyVisibleItemPosition())
//endregion