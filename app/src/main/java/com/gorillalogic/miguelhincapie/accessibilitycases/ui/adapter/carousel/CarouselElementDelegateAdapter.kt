package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.domain.entities.CarouselElement
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.DelegateAdapter
import kotlinx.android.synthetic.main.carousel_element_item.view.*

class CarouselElementDelegateAdapter(private val listener: OnCarouselElementListener) :
    DelegateAdapter<CarouselElementDelegateAdapter.ViewHolder, CarouselElementViewType> {

    interface OnCarouselElementListener {
        fun onCarouselElementClicked(carouselElement: CarouselElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: CarouselElementViewType) {
        viewHolder.bind(viewType, listener)
    }

    class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.carousel_element_item, viewGroup, false
        )
    ) {
        fun bind(
            carouselElementViewType: CarouselElementViewType,
            listener: OnCarouselElementListener
        ) {
            itemView.accessibility_state.text = carouselElementViewType.carouselElement.value
            itemView.setOnClickListener { listener.onCarouselElementClicked(carouselElementViewType.carouselElement) }
        }
    }
}