package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.model.SuccessFailure
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    var guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<SuccessFailure>()
    var saveGuest: LiveData<SuccessFailure> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            val inserted = repository.insert(guest)

            if (inserted) {
                _saveGuest.value = SuccessFailure(true, "Adicionado com sucesso")
            } else {
                _saveGuest.value = SuccessFailure(false, "Falha na inserção")
            }
        } else {
            val updated = repository.update(guest)

            if (updated) {
                _saveGuest.value = SuccessFailure(true, "Atualizado com sucesso")
            } else {
                _saveGuest.value = SuccessFailure(false, "Falha na atualização")
            }
        }
    }

    fun get(guestId: Int) {
        guestModel.value = repository.get(guestId)
    }
}