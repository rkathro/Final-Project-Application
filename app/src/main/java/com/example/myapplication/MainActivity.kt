package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.setup.AccountSetupFragment
import com.example.myapplication.ui.setup.UserDataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: UserDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_account, R.id.navigation_history, R.id.navigation_settings, R.id.navigation_account_setup))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        /*Resets the app to think every login is the users First login. Use for Demonstration purposes
        resetSharedPref()
        if (isFirstLogin()) {
            navigateToAccountSetup()
        }*/


    }

    private fun isFirstLogin(): Boolean {
        return sharedPreferences.getBoolean("first_login", true)
    }
    private fun navigateToAccountSetup() {
        // Inflate AccountSetupFragment
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.navigate(R.id.navigation_account_setup)
    }
    private fun resetSharedPref() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("first_login", true)
        editor.apply()
    }

}