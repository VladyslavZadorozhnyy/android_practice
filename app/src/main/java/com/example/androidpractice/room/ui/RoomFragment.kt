package com.example.androidpractice.room.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.abstractions.BaseFragment
import com.example.androidpractice.databinding.FragmentRoomBinding
import com.example.androidpractice.room.viewmodel.RoomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomFragment : BaseFragment() {
    lateinit var binding: FragmentRoomBinding
    val viewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRoomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupButtons()
        setupInputForm()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runInBackground {
            delay(4000)
            viewModel.initialize(activity?.applicationContext)
        }
    }

    private fun setupButtons() {
        setupInfoButton()
        setupBackButton()
        setupCommitButton()
        setupChooseActivityButtons()
    }

    private fun setupInfoButton() {
        val toastHint = "This is Room fragment, explore CRUD operations here"

        binding.infoButton.setOnClickListener {
            Toast.makeText(context, toastHint, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            (activity as Routable).redirectBack()
        }
    }

    private fun setupCommitButton() {
        binding.commitButton.setOnClickListener {
            runInBackground {
                withContext(Dispatchers.Default) {
                    val result = viewModel.processAction(
                        binding.userIdInput.text.toString(),
                        binding.userFirstName.text.toString(),
                        binding.userLastName.text.toString()
                    )

                    binding.inputFormTitle.text = "Result is $result"
                    giveInputFieldsVisibility(View.GONE)
                }
            }
        }
    }

    private fun setupChooseActivityButtons() {
        setupCreateUser()
        setupReadUser()
        setupUpdateUser()
        setupDeleteUser()
    }

    private fun setupCreateUser() {
        binding.createUser.setOnClickListener {
            viewModel.setChosenAction(RoomViewModel.RoomAction.Create)

            disableInputField(binding.userIdInput)
            enableInputField(binding.userFirstName)
            enableInputField(binding.userLastName)
            giveInputFieldsVisibility(View.VISIBLE)
            binding.inputFormTitle.text = "Chosen action: Create user"
        }
    }

    private fun setupReadUser() {
        binding.readUsers.setOnClickListener {
            viewModel.setChosenAction(RoomViewModel.RoomAction.Read)

            disableInputField(binding.userIdInput)
            disableInputField(binding.userFirstName)
            disableInputField(binding.userLastName)
            giveInputFieldsVisibility(View.VISIBLE)
            binding.inputFormTitle.text = "Chosen action: Read user"
        }
    }

    private fun setupUpdateUser() {
        binding.updateUser.setOnClickListener {
            viewModel.setChosenAction(RoomViewModel.RoomAction.Update)

            enableInputField(binding.userIdInput)
            enableInputField(binding.userFirstName)
            enableInputField(binding.userLastName)
            giveInputFieldsVisibility(View.VISIBLE)
            binding.inputFormTitle.text = "Chosen action: Update user"
        }
    }

    private fun setupDeleteUser() {
        binding.deleteUser.setOnClickListener {
            viewModel.setChosenAction(RoomViewModel.RoomAction.Delete)

            enableInputField(binding.userIdInput)
            disableInputField(binding.userFirstName)
            disableInputField(binding.userLastName)
            giveInputFieldsVisibility(View.VISIBLE)
            binding.inputFormTitle.text = "Chosen action: Delete user"
        }
    }

    private fun setupInputForm() {
        binding.inputFormTitle.text = "Please, choose activity"
        giveInputFieldsVisibility(View.GONE)
    }

    private fun giveInputFieldsVisibility(visibility: Int) {
        binding.userIdInput.visibility = visibility
        binding.userLastName.visibility = visibility
        binding.userFirstName.visibility = visibility

        binding.userIdLabel.visibility = visibility
        binding.userLastNameLabel.visibility = visibility
        binding.userFirstNameLabel.visibility = visibility
    }

    private fun disableInputField(view: EditText) {
        view.isEnabled = false
        view.setBackgroundColor(Color.GRAY)
    }

    private fun enableInputField(view: EditText) {
        view.isEnabled = true
        view.setBackgroundColor(Color.WHITE)
    }

    private fun runInBackground(block: suspend () -> Unit) {
        viewModel.viewModelScope.launch {
            binding.inputForm.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            block.invoke()
            binding.progressBar.visibility = View.GONE
            binding.inputForm.visibility = View.VISIBLE
        }
    }
}