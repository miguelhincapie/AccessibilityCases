package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel

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
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.BaseAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.DelegateAdapter
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.RecyclerViewType

@Suppress("UNCHECKED_CAST")
class CarouselAdapter(listener: CarouselElementDelegateAdapter.OnCarouselElementListener) :
    BaseAdapter() {
    init {
        delegateAdapters = SparseArrayCompat()
        delegateAdapters.append(
            CarouselElementViewType.ITEM.hashCode(),
            CarouselElementDelegateAdapter(
                listener
            ) as DelegateAdapter<RecyclerView.ViewHolder, RecyclerViewType>
        )
    }

    fun scrollToGridSelectedItem(rv: RecyclerView, selectedPosition: Int) = with(rv) {
        this.post {
            (this.layoutManager as? LinearLayoutManager)?.let {
                if (selectedPosition > RecyclerView.NO_POSITION) {
                    val firstItemVisible = it.findFirstCompletelyVisibleItemPosition()
                    val lastItemVisible = it.findLastCompletelyVisibleItemPosition()
                    if (selectedPosition < firstItemVisible || selectedPosition > lastItemVisible) {
                        this.smoothScrollToPosition(selectedPosition)
                    }
                }
            }
        }
    }
}