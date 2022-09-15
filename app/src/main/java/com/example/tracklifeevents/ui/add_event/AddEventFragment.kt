package com.example.tracklifeevents.ui.add_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tracklifeevents.databinding.AddEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEventFragment : Fragment() {
    private lateinit var binding: AddEventBinding
    private val viewModel: AddEventVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddEventBinding.inflate(inflater, container, false)
        return binding.root
    }
}