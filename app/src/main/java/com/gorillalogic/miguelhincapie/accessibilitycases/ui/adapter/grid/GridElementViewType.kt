package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid

import com.gorillalogic.miguelhincapie.domain.entities.GridElement
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.RecyclerViewType

class GridElementViewType(val gridElement: GridElement) :
    RecyclerViewType {

    override fun getViewTypeId() = gridElement.id
    override fun getViewType() = ITEM.hashCode()

    companion object {
        const val ITEM = "GridElementViewType"
    }
}