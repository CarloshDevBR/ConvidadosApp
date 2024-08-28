package com.example.convidados.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGuestSave.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        defaultValues()

        observe()
        loadData()
    }

    private fun defaultValues() {
        binding.radioPresent.isChecked = true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_guest_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val guest = GuestModel(guestId, name, presence)

            viewModel.save(guest)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)

            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this) {
            if (it.success) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)

            viewModel.get(guestId)
        }
    }
}