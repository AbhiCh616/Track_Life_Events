package com.example.tracklifeevents.ui.events_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tracklifeevents.R
import com.example.tracklifeevents.databinding.EventListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventListFragment : Fragment() {
    private lateinit var binding: EventListBinding
    private val viewModel: EventListVM by viewModels()
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventListBinding.inflate(inflater, container, false)

        adapter = EventsAdapter(eventSet = mutableListOf())
        binding.rvEvents.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { eventsList ->
                    adapter.updateData(updatedEventSet = eventsList)
                }
            }
        }

        binding.addEventButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_eventList_to_addEvent)
        }
    }
}