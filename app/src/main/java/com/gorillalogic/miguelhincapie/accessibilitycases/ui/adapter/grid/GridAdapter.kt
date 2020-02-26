package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid

import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.*

@Suppress("UNCHECKED_CAST")
class GridAdapter(listener: GridElementDelegateAdapter.OnGridElementListener) : BaseAdapter() {
    init {
        delegateAdapters = SparseArrayCompat()
        delegateAdapters.append(
            GridElementViewType.ITEM.hashCode(),
            GridElementDelegateAdapter(
                listener
            ) as DelegateAdapter<RecyclerView.ViewHolder, RecyclerViewType>
        )
    }
}