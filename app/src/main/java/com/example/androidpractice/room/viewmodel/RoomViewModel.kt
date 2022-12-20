package com.example.androidpractice.room.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidpractice.room.businesslogic.AppDatabase
import com.example.androidpractice.room.businesslogic.User
import com.example.androidpractice.room.businesslogic.UserDao

class RoomViewModel : ViewModel() {
    private var chosenAction: RoomAction? = null
    lateinit var database: AppDatabase
    lateinit var userDao: UserDao

    fun setChosenAction(action: RoomAction) {
        chosenAction = action
    }

    fun initialize(applicationContext: Context?) {
        applicationContext?.let {
            database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            userDao = database.userDao()
        }
    }

    fun processAction(userId: String, userName: String, userSurname: String): String {
        val userIdFiltered by lazy {
            if (userId.isEmpty()) {
                0
            } else {
                userId.toInt()
            }
        }

        chosenAction?.let {
            return when (chosenAction!!) {
                RoomAction.Delete -> deleteUser(userIdFiltered)
                RoomAction.Create -> createUser(userName, userSurname)
                RoomAction.Read -> getAllUsers()
                RoomAction.Update -> updateUser(userIdFiltered, userName, userSurname)
            }
        }
        return "chosenAction variable is null"
    }

    private fun getAllUsers(): String {
        var result = "Users are: \n"
        userDao.getAll().forEach {
            result += it.toString()
            result += "\n"
        }

        return result
    }

    private fun createUser(userName: String, userSurname: String): String {
        val newUser = User(0, userName, userSurname)

        return try {
            userDao.insertUser(newUser)
            "New user successfully added"
        } catch (exception: Throwable) {
            "New user was not added. Exception: ${exception.message}"
        }
    }

    private fun updateUser(userId: Int, userName: String, userSurname: String): String {
        val updatedUser = User(userId, userName, userSurname)

        return try {
            userDao.updateUser(updatedUser)
            "User successfully modified"
        } catch (exception: Throwable) {
            "User was not modified"
        }
    }

    private fun deleteUser(userId: Int): String {
        return try {
            userDao.deleteUser(userId)
            "User was successfully removed"
        } catch (exception: Throwable) {
            "User was not removed"
        }
    }

    enum class RoomAction {
        Create,
        Read,
        Update,
        Delete
    }
}