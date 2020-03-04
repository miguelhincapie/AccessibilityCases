package com.gorillalogic.miguelhincapie.accessibilitycases.domain.di

import android.content.Context
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.KeyEventHandler
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.accessibility.TalkBackFacade
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class DomainModule {

    @Provides
    fun provideKeyEventHandler(): KeyEventHandler {
        return KeyEventHandler()
    }

    @Provides
    fun provideTalkBackFacade(context: Context): TalkBackFacade {
        return TalkBackFacade(WeakReference(context))
    }
}