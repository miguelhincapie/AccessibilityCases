package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

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
        talkBackState.value = if (!talkBackFacade.isTalkBackEnabled()) talkBackFacade.enableTalkBack() else true
    }

    fun disableTalkBack() {
        talkBackState.value = if (talkBackFacade.isTalkBackEnabled()) !talkBackFacade.disableTalkBack() else false
    }

    fun talkBackStateLiveData(): LiveData<Boolean> = talkBackState
}