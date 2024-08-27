package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel) = repository.insert(guest)

    fun update(guest: GuestModel) = repository.update(guest)

    fun delete(guest: GuestModel) = repository.delete(guest.id)
}