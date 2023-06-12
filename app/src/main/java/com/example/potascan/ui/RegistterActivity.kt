package com.example.potascan.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.potascan.R
import com.example.potascan.databinding.ActivityRegistterBinding
import com.example.potascan.ui.ViewModel.RegisterViewModel

class RegistterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registter)
    }
}