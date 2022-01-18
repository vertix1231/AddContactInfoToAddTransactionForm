package com.test.kerja.addcontactinfotoaddtransactionform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.kerja.addcontactinfotoaddtransactionform.databinding.ActivityDetailContactBinding

class DetailContactActivity : AppCompatActivity() {
    companion object {
        const val IN_USERNAME = "in_username"
        const val IN_NUMBER = "in_number"
    }
    private lateinit var binding: ActivityDetailContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra(IN_USERNAME)
        val nomor = intent.getStringExtra(IN_NUMBER)

        binding.textViewNama.text = nama
        binding.textViewNomor.text = nomor



    }


}