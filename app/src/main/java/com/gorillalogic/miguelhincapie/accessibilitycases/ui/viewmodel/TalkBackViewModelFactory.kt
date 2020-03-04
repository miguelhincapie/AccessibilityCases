package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.KeyEventHandler
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.TalkBackFacade

class TalkBackViewModelFactory(
    private val keyEventHandler: KeyEventHandler,
    private val talkBackFacade: TalkBackFacade
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TalkBackViewModel(keyEventHandler, talkBackFacade) as T
}