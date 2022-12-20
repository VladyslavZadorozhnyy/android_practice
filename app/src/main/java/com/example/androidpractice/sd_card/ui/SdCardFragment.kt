package com.example.androidpractice.sd_card.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidpractice.databinding.FragmentSdCardBinding
import com.example.androidpractice.sd_card.businesslogic.SdCardViewModel
import com.example.androidpractice.services.utils.ToastUtils
import java.io.File
import java.io.FileWriter

class SdCardFragment : Fragment() {
    private lateinit var binding: FragmentSdCardBinding
    private val viewModel: SdCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSdCardBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()

//        val dir = File(context?.filesDir, "sd_card_dir")
//        context?.filesDir?.listFiles()
//        dir.listFiles()?.any {
//            it.name == ""
//        }

//        if (!dir.exists()) {
//            dir.mkdir()
//            Log.d("AAADIP", "Dir not exists, creating one")
//        } else {
//            Log.d("AAADIP", "Dir already exists")
//        }

//        try {
//            val file = File(dir, "some_example_file_1.txt")
//            val fileWriter = FileWriter(file)
//
//
//            fileWriter.append("Some example body updated\n")
//            fileWriter.flush()
//            fileWriter.close()
//            Log.d("AAADIP", "File done successfully, path: ${dir.path}")
//        } catch (e: Throwable) {
//            Log.d("AAADIP", "Error 0: $e")
//            Log.d("AAADIP", "Error 1: ${e.message}")
//        }
    }

    private fun setupButtons() {
        binding.createFileButton.setOnClickListener {
            disableCRUDButtons()
            viewModel.updateChosenAction(SdCardViewModel.SdCardAction.Create)
            enableButton(it)
        }

        binding.readFileButton.setOnClickListener {
            disableCRUDButtons()
            viewModel.updateChosenAction(SdCardViewModel.SdCardAction.Read)
            enableButton(it)
        }

        binding.updateFileButton.setOnClickListener {
            disableCRUDButtons()
            viewModel.updateChosenAction(SdCardViewModel.SdCardAction.Update)
            enableButton(it)
        }

        binding.deleteFileButton.setOnClickListener {
            disableCRUDButtons()
            viewModel.updateChosenAction(SdCardViewModel.SdCardAction.Delete)
            enableButton(it)
        }

        binding.confirmButton.setOnClickListener {
            viewModel.processAction(
                binding.fileNameForm.text.toString(),
                binding.fileContentForm.text.toString(),
                requireContext()
            )

            if (viewModel.errorTitle.isNotEmpty() || viewModel.errorMessage.isNotEmpty()) {
                showDialog(viewModel.errorTitle, viewModel.errorMessage)
            } else {
                ToastUtils.showToast(requireContext(), viewModel.successMessage)
            }
        }
    }

    private fun disableCRUDButtons() {
        binding.createFileButton.setBackgroundColor(Color.BLACK)
        binding.readFileButton.setBackgroundColor(Color.BLACK)
        binding.updateFileButton.setBackgroundColor(Color.BLACK)
        binding.deleteFileButton.setBackgroundColor(Color.BLACK)
    }

    private fun enableButton(button: View) {
        button.setBackgroundColor(Color.BLUE)
    }

    private fun showDialog(dialogTitle: String, dialogMessage: String) {
        AlertDialog.Builder(context)
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("Got it") { _, _ -> }
            .show()
    }
}