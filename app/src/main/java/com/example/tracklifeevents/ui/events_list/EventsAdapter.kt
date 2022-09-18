package com.example.tracklifeevents.ui.events_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tracklifeevents.R
import com.example.tracklifeevents.databinding.EventItemBinding

class EventsAdapter(
    private val eventSet: MutableList<EventItemData>
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    class ViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventSet[position]
        with(holder.binding) {
            eventName.text = event.name
            eventDaysLeft.text =
                holder.itemView.context.getString(R.string.days_left_d).format(event.daysLeft)
            eventImage.load(event.imageUri)
        }
    }

    override fun getItemCount() = eventSet.size

    fun updateData(updatedEventSet: List<EventItemData>) {
        val diffCallback = EventItemDiffCallback(eventSet, updatedEventSet)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        eventSet.clear()
        eventSet.addAll(updatedEventSet)
        diffResult.dispatchUpdatesTo(this)
    }

}