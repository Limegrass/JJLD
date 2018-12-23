package com.waifusims.wanicchou.widgets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import data.room.entity.Definition

abstract class ListViewAdapter<T, VH : ListViewAdapter.ViewHolder<T>>(private val list : List<T>,
                                                             private val viewHolderConstructor: (View) -> VH,
                                                             private val layoutID : Int)
    : RecyclerView.Adapter<VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val context = parent.context
        val wordLayoutId = layoutID
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(wordLayoutId, parent, false)
        return viewHolderConstructor(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int){
        viewHolder.bind(list[position])
    }

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(value: T)
    }
}
