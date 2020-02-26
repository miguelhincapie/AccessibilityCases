package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel

import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.*

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