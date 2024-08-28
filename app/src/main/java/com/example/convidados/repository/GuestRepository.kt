package com.example.convidados.repository

import android.content.Context
import com.example.convidados.model.GuestModel

class GuestRepository(context: Context) {
    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun get(guestId: Int): GuestModel = guestDataBase.get(guestId)

    fun getAll(): List<GuestModel> = guestDataBase.getAll()

    fun getPresent(): List<GuestModel> = guestDataBase.getPresent()

    fun getAbsent(): List<GuestModel> = guestDataBase.getAbsent()

    fun insert(guest: GuestModel): Boolean = guestDataBase.insert(guest) > 0

    fun update(guest: GuestModel): Boolean = guestDataBase.update(guest) > 0

    fun delete(guestId: Int) {
        val guest = get(guestId)

        guestDataBase.delete(guest)
    }
}