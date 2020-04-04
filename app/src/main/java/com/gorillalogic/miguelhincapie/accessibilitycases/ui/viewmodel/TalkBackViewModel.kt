package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

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

    fun enableTalkBack() {
        TalkBackState.value = if (!talkBackFacade.isTalkBackEnabled()) talkBackFacade.enableTalkBack() else true
    }

    fun disableTalkBack() {
        TalkBackState.value = if (talkBackFacade.isTalkBackEnabled()) !talkBackFacade.disableTalkBack() else false
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

object TalkBackState : MutableLiveData<Boolean>()