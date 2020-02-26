package com.gorillalogic.miguelhincapie.accessibilitycases.ui.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<RecyclerViewType>()
        private set
    lateinit var delegateAdapters: SparseArrayCompat<DelegateAdapter<RecyclerView.ViewHolder, RecyclerViewType>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters[viewType]?.onCreateViewHolder(parent)!!
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val delegateAdapter = delegateAdapters[item.getViewType()]
        delegateAdapter?.onBindViewHolder(holder, item)
    }

    open fun setElements(newListOfItems: MutableList<RecyclerViewType>) {
        items = newListOfItems
        notifyDataSetChanged()
    }
}