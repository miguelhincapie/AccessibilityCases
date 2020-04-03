package com.gorillalogic.miguelhincapie.accessibilitycases.ui.di

import com.gorillalogic.miguelhincapie.accessibilitycases.domain.TalkBackFacade
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.viewmodel.TalkBackViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @ActivityScope
    @Provides
    fun provideTalkBackViewModelFactory(
        talkBackFacade: TalkBackFacade
    ): TalkBackViewModelFactory {
        return TalkBackViewModelFactory(talkBackFacade)
    }
}