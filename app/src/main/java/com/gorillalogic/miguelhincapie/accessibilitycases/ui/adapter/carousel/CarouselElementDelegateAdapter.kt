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
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.miguelhincapie.accessibilitycases.R
import com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.DelegateAdapter
import com.gorillalogic.miguelhincapie.domain.entities.CarouselElement
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
        viewHolder.bindAccessibility(viewType)
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
            itemView.carousel_title.text = carouselElementViewType.carouselElement.value
            itemView.setOnClickListener { listener.onCarouselElementClicked(carouselElementViewType.carouselElement) }
        }

        fun bindAccessibility(carouselElementViewType: CarouselElementViewType) {
            // Do additional accessibility things
        }
    }
}