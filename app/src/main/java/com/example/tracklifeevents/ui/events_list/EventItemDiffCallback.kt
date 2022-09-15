package com.example.tracklifeevents.ui.events_list

import androidx.recyclerview.widget.DiffUtil

class EventItemDiffCallback(
    private val oldList: List<EventItemData>,
    private val newList: List<EventItemData>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}