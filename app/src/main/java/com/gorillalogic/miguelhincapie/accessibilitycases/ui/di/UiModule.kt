package com.gorillalogic.miguelhincapie.accessibilitycases.ui.di

import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModelFactory
import com.gorillalogic.miguelhincapie.domain.accessibility.KeyEventHandler
import com.gorillalogic.miguelhincapie.domain.accessibility.TalkBackFacade
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @ActivityScope
    @Provides
    fun provideTalkBackViewModelFactory(
        keyEventHandler: KeyEventHandler,
        talkBackFacade: TalkBackFacade
    ): TalkBackViewModelFactory {
        return TalkBackViewModelFactory(keyEventHandler, talkBackFacade)
    }
}