package com.gorillalogic.miguelhincapie.accessibilitycases.ui.di

import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.KeyEventHandler
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.TalkBackFacade
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModelFactory
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