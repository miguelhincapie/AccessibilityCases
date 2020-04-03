package com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade

class TalkBackViewModelFactory(
    private val talkBackFacade: TalkBackFacade
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TalkBackViewModel(talkBackFacade) as T
}