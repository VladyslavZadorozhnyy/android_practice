package com.example.androidpractice.sd_card.businesslogic

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class SdCardViewModel : ViewModel() {
    private var chosenAction: SdCardAction? = null
    private val DIR_NAME = "sd_card_dir"

    var successMessage = ""

    var errorTitle: String = ""
    var errorMessage: String = ""

    fun updateChosenAction(newValue: SdCardAction) {
        chosenAction = newValue
    }

    fun processAction(fileName: String, fileContent: String, context: Context) {
        successMessage = ""
        errorTitle = ""
        errorMessage = ""

        val fileNamee: String = if (!fileName.contains(".txt")) {
            "$fileName.txt"
        } else {
            fileName
        }

        if (fileName.isEmpty()) {
            errorTitle = "File name is empty"
            errorMessage = "Please, fill in the file name form"
            return
        } else if (fileContent.isEmpty()) {
            errorTitle = "File content is empty"
            errorMessage = "Please, fill in the file content form"
            return
        } else if (chosenAction == SdCardAction.Create) {
            successMessage = createFile(context, fileNamee, fileContent)
        } else if (fileExists(fileNamee, context)) {
            successMessage = procesRUD(context, fileNamee, fileContent)
        } else {
            errorTitle = "File does not exist"
            errorMessage = "File name you've input is not valid"
        }
    }

    private fun fileExists(fileName: String, context: Context): Boolean {
        val dir = File(context.filesDir, DIR_NAME)
        return dir.listFiles()?.any {
            it.name == fileName
        } ?: false
    }

    private fun createFile(context: Context, fileName: String, fileContent: String): String {
        val dir = File(context.filesDir, DIR_NAME)

        if (!dir.exists()) {
            dir.mkdir()
        }

        return try {
            val file = File(dir, fileName)
            val fileWriter = FileWriter(file)

            fileWriter.append("$fileContent\n")
            fileWriter.flush()
            fileWriter.close()
            "Created at: ${dir.path}$fileName"
        } catch (e: Throwable) {
            "Error, message: ${e.message}"
        }
    }

    fun procesRUD(
        context: Context,
        fileName: String,
        fileContent: String
    ): String {
        var result = ""
        val dir = File(context.filesDir, DIR_NAME)
        val file = File(dir, fileName)

        if (!dir.exists()) {
            dir.mkdir()
        }

        if (chosenAction == SdCardAction.Read) {
            val fileReader = FileReader(file)
            result = fileReader.readText()
            fileReader.close()
        } else if (chosenAction == SdCardAction.Delete) {
            file.delete()
            result = "Deleted successfully"
        } else {
            file.delete()
            createFile(context, fileName, fileContent)
            result = "Updated successfully"
        }

        return result
    }

    enum class SdCardAction {
        Create,
        Read,
        Update,
        Delete
    }
}