package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.CarouselKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.GeneralKeyEventDelegate
import com.gorillalogic.miguelhincapie.accessibilitycases.accessibility.delegate.GridKeyEventDelegate
import com.gorillalogic.miguelhincapie.domain.accessibility.KeyEventHandler
import com.gorillalogic.miguelhincapie.domain.accessibility.TalkBackFacade
import java.lang.ref.WeakReference

class TalkBackViewModel(
    private val keyEventHandler: KeyEventHandler,
    private val talkBackFacade: TalkBackFacade
) : ViewModel() {

    init {
        TalkBackState.value = talkBackFacade.isTalkBackEnabled()
        keyEventHandler.addKeyEventDelegate(GeneralKeyEventDelegate())
        keyEventHandler.addKeyEventDelegate(GridKeyEventDelegate())
        keyEventHandler.addKeyEventDelegate(CarouselKeyEventDelegate())
    }

    fun dispatchKeyEvent(event: KeyEvent, currentFocusWR: WeakReference<View>): Boolean? {
        return when {
            keyEventHandler.switchAccessibilityKeyPressed(event) -> {
                TalkBackState.value = talkBackFacade.switchTalkBackState()
                true
            }
            talkBackFacade.isTalkBackEnabled() -> keyEventHandler.handleEvent(event, currentFocusWR)
            else -> null
        }
    }

    fun talkBackStateLiveData(): LiveData<Boolean> = TalkBackState
}

object TalkBackState: MutableLiveData<Boolean>()