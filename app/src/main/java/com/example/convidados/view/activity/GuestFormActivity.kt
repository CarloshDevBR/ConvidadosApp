package com.example.convidados.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGuestSave.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        defaultValues()
    }

    private fun defaultValues() {
        binding.radioPresent.isChecked = true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_guest_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            viewModel.insert(GuestModel(0, name, presence))
        }
    }
}