package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.domain.entities.GridElement
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.DelegateAdapter
import kotlinx.android.synthetic.main.grid_element_item.view.*

class GridElementDelegateAdapter(private val listener: OnGridElementListener) :
    DelegateAdapter<GridElementDelegateAdapter.ViewHolder, GridElementViewType> {

    interface OnGridElementListener {
        fun onGridElementClicked(gridElement: GridElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: GridElementViewType) {
        viewHolder.bind(viewType, listener)
    }

    class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.grid_element_item, viewGroup, false
        )
    ) {
        fun bind(gridElementViewType: GridElementViewType, listener: OnGridElementListener) {
            itemView.accessibility_state.text = gridElementViewType.gridElement.value
            itemView.setOnClickListener { listener.onGridElementClicked(gridElementViewType.gridElement) }
        }
    }
}