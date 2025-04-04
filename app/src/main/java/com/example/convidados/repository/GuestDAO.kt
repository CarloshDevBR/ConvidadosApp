package com.example.convidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.convidados.model.GuestModel

@Dao
interface GuestDAO {
    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :guestId")
    fun get(guestId: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>
}