package com.example.android4a.data.local

import com.example.android4a.data.local.models.UserLocal
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android4a.domain.entities.User

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM userLocal")
    fun getAll(): List<UserLocal>


    @Query("SELECT * FROM userLocal WHERE email LIKE :email LIMIT 1")
    fun findByName(email: String): UserLocal

    @Insert
    fun insert(user: UserLocal)

    @Delete
    fun delete(user: UserLocal)
}