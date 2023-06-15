package com.example.potascan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.potascan.R
import com.example.potascan.ViewModel.MainViewModel
import com.example.potascan.ViewModel.RegisterViewModel
import com.example.potascan.ViewModel.ViewModelFactoryArticle
import com.example.potascan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFirstInstall = false
    private lateinit var mainViewModel: MainViewModel
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
            R.id.instruksi -> {
                showInstructionDialog()
            }

            R.id.logOut -> {
                showDialogChoose()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogChoose() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Keluar dari aplikasi")
        builder.setMessage("Apa anda yakin?")

        builder.setNegativeButton("Yes") { _, _ ->
            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.logout()
            }
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        builder.setPositiveButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showInstructionDialog() {
        AlertDialog.Builder(this).apply {
            val dialogView =
                LayoutInflater.from(this@MainActivity).inflate(R.layout.instruction_dialog, null)
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