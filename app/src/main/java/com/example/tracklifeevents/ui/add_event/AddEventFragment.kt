package com.example.tracklifeevents.ui.add_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tracklifeevents.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventNameTextField.let { textField ->
            textField.setText(viewModel.eventName)
            textField.doAfterTextChanged { editable ->
                editable?.let {
                    viewModel.eventName = editable.toString()
                }
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save_btn -> {
                    viewModel.onSaveClick()
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }
}