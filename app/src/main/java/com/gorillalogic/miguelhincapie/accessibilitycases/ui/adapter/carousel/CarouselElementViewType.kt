package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel

import com.gorillalogic.miguelhincapie.accessibilitycases.domain.entities.CarouselElement
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.RecyclerViewType

class CarouselElementViewType(val carouselElement: CarouselElement) :
    RecyclerViewType {

    override fun getViewTypeId() = carouselElement.id
    override fun getViewType() = ITEM.hashCode()

    companion object {
        const val ITEM = "CarouselElementViewType"
    }
}