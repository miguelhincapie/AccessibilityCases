package com.gorillalogic.miguelhincapie.accessibilitycases.ui.di

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