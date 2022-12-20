package com.example.androidpractice.room.businesslogic

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IS :uId LIMIT 1")
    fun findById(uId: Int): User

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user WHERE uid IS :uId")
    fun deleteUser(uId: Int)
}