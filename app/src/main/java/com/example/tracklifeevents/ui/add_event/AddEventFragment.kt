package com.example.tracklifeevents.ui.add_event

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.tracklifeevents.R
import com.example.tracklifeevents.databinding.AddEventBinding
import com.google.modernstorage.photopicker.PhotoPicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate

@androidx.annotation.OptIn(androidx.core.os.BuildCompat.PrereleaseSdkCheck::class)
@AndroidEntryPoint
class AddEventFragment : Fragment() {
    private lateinit var binding: AddEventBinding
    private val viewModel: AddEventVM by viewModels()
    private lateinit var photoPicker: ActivityResultLauncher<PhotoPicker.Args>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddEventBinding.inflate(inflater, container, false)
        photoPicker = registerForActivityResult(PhotoPicker()) { uris ->
            viewModel.setImage(uris[0])
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventImage.visibility = View.GONE

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

        binding.eventDateLayout.let { layout ->
            layout.setEndIconOnClickListener {
                val today = LocalDate.now()
                val year = viewModel.eventDate.value?.year ?: today.year
                val month = viewModel.eventDate.value?.monthValue ?: today.monthValue
                val day = viewModel.eventDate.value?.dayOfMonth ?: today.dayOfMonth

                val onDateSetListener =
                    DatePickerDialog.OnDateSetListener { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        viewModel.setDate(
                            year = selectedYear,
                            month = selectedMonth,
                            day = selectedDay
                        )
                    }
                val datePickerDialog =
                    DatePickerDialog(requireContext(), onDateSetListener, year, month, day)
                datePickerDialog.show()
            }
        }

        binding.selectImageBtn.let { button ->
            button.setOnClickListener {
                photoPicker.launch(PhotoPicker.Args(PhotoPicker.Type.IMAGES_ONLY, 1))
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventDateText.collect { dateText ->
                    binding.eventDateTextField.setText(dateText)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventImageUri.collect { imageUri ->
                    if(imageUri != null) {
                        binding.eventImage.visibility = View.VISIBLE
                        binding.eventImage.load(imageUri)
                    } else {
                        binding.eventImage.visibility = View.GONE
                    }
                }
            }
        }

        binding.eventDateTextField.let { textField ->
            textField.isEnabled = false
        }
    }
}