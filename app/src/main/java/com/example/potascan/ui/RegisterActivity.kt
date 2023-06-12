package com.example.potascan.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.potascan.R
import com.example.potascan.databinding.ActivityRegisterBinding
import com.example.potascan.ui.ViewModel.RegisterViewModel
import com.example.potascan.data.Result
import com.example.potascan.ui.ViewModel.ViewModelFactoryArticle

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactoryArticle.getInstance(dataStore)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupAction()


    }

    private fun setupAction() {
        binding.btnRegis.setOnClickListener {
            val name = binding.evName.text.toString()
            val email = binding.evEmail.text.toString()
            val password = binding.evPass.text.toString()
            val confirmPass = binding.evConfirmPass.text.toString()
            when {
                name.isEmpty() -> {
                    binding.evName.error = "Masukkan nama"
                }
                email.isEmpty() -> {
                    binding.evEmail.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.evPass.error = "Masukkan password"
                }
                confirmPass.isEmpty() -> {
                    binding.evConfirmPass.error = "Masukkan password"
                }
                else -> {
                    Log.d(TAG, "Proses login: ")
                    registerViewModel.register(name, email, password,confirmPass).observe(this) {
                        if (it!=null) {
                            when(it) {
                                is Result.Loading -> {
                                    Toast.makeText(this@RegisterActivity, "Memproses registrasi", Toast.LENGTH_SHORT).show()
                                }
                                is Result.Success -> {
                                    AlertDialog.Builder(this).apply {
                                        setTitle("Yeah!")
                                        setMessage("Akunnya sudah jadi nih. Yuk, login dan bagikan ceritamu!")
                                        setPositiveButton("Lanjut") { _, _ ->
                                            val intent =
                                                Intent(this@RegisterActivity, LoginActivity::class.java)
                                            intent.flags =
                                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            startActivity(intent)
                                        }
                                        create()
                                        show()
                                    }
                                }
                                is Result.Error -> {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        it.error,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}