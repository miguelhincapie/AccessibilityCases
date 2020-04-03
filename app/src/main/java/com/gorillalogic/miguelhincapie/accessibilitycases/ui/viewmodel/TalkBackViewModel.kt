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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade

class TalkBackViewModel(private val talkBackFacade: TalkBackFacade) : ViewModel() {

    private val talkBackState = MutableLiveData<Boolean>()

    init {
        talkBackState.value = talkBackFacade.isTalkBackEnabled()
    }

    fun enableTalkBack() {
        talkBackState.value =
            if (!talkBackFacade.isTalkBackEnabled()) talkBackFacade.enableTalkBack() else true
    }

    fun disableTalkBack() {
        talkBackState.value =
            if (talkBackFacade.isTalkBackEnabled()) !talkBackFacade.disableTalkBack() else false
    }

    fun talkBackStateLiveData(): LiveData<Boolean> = talkBackState
}