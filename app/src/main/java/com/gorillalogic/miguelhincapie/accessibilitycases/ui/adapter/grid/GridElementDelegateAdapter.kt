package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter.grid

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
import com.gorillalogic.miguelhincapie.domain.entities.GridElement
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
        viewHolder.bindAccessibility(viewType)
    }

    class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.grid_element_item, viewGroup, false
        )
    ) {
        fun bind(gridElementViewType: GridElementViewType, listener: OnGridElementListener) {
            itemView.grid_title.text = gridElementViewType.gridElement.value
            itemView.setOnClickListener { listener.onGridElementClicked(gridElementViewType.gridElement) }
        }

        fun bindAccessibility(gridElementViewType: GridElementViewType) {
            // Do additional accessibility things
        }
    }
}