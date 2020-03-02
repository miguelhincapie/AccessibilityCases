package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TalkBackViewModel: ViewModel() {
    val talkBackState = TalkBackState
}

object TalkBackState : MutableLiveData<Boolean>()