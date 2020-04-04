package com.gorillalogic.miguelhincapie.domain.di

import dagger.Module

@Module
class DomainModule {

    /**
     * Due there is no complexity building KeyEventHandler nor TalkBackFacade they are marked
     * with @Inject.
     */
//    @Provides
//    fun provideKeyEventHandler(): KeyEventHandler {
//        return KeyEventHandler()
//    }
//
//    @Provides
//    fun provideTalkBackFacade(context: Context): TalkBackFacade {
//        return TalkBackFacade(WeakReference(context))
//    }
}