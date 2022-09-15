package com.example.tracklifeevents.ui.events_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tracklifeevents.R
import com.example.tracklifeevents.databinding.EventListBinding

class EventListFragment : Fragment() {
    private lateinit var binding: EventListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEventButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_eventList_to_addEvent)
        }
    }
}