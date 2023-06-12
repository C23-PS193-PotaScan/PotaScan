package com.example.potascan.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.potascan.R
import com.example.potascan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFirstInstall = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isFirstInstall) {
            showInstructionDialog()
            isFirstInstall = true
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_camera, R.id.navigation_articles
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.instruksi-> {
                showInstructionDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showInstructionDialog() {
        AlertDialog.Builder(this).apply {
            val dialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.instruction_dialog, null)
            val dialogBuilder = AlertDialog.Builder(this@MainActivity)
                .setView(dialogView)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            val alertDialog = dialogBuilder.create()
            alertDialog.window?.setBackgroundDrawableResource(R.drawable.background_alert)
            alertDialog.show()
        }
    }
}