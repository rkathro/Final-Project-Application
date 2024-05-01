package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.account.AccountFragment
import com.example.myapplication.ui.setup.AccountSetupFragment
import com.example.myapplication.ui.setup.CompanyData
import com.example.myapplication.ui.setup.UserDataViewModel

class MainActivity : AppCompatActivity() {
    private val userDataViewModel: UserDataViewModel by viewModels()
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

        userDataViewModel.addUserCompanyData(CompanyData(R.drawable.charlotte_49ers_1, "Charlotte", "fortyniners"))
        userDataViewModel.addUserCompanyData(CompanyData(R.drawable.wells_fargo, "Wells Fargo", "2013"))
        userDataViewModel.addUserCompanyData(CompanyData(R.drawable.facebook_3_2, "Facebook", "zuckerberg"))

        userDataViewModel.addCompanyData(CompanyData(R.drawable.charlotte_49ers_1, "Charlotte", "fortyniners"))
        userDataViewModel.addCompanyData(CompanyData(R.drawable.wells_fargo, "Wells Fargo", "2013"))
        userDataViewModel.addCompanyData(CompanyData(R.drawable.facebook_3_2, "Facebook", "zuckerberg"))
        userDataViewModel.addCompanyData(CompanyData(R.drawable.gmail_svgrepo_com, "Gmail", "google"))

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        //Resets the app to think every login is the users First login. Use for Demonstration purposes
        //Use wisely its a pain in the ass after I implemented all the checks
        resetSharedPref()
        if (isFirstLogin()) {
            navigateToAccountSetup()
        }
    }

    private fun isFirstLogin(): Boolean {
        return sharedPreferences.getBoolean("first_login", true)
    }
    private fun navigateToAccountSetup() {
        navController.navigate(R.id.navigation_account_setup)
    }
    private fun resetSharedPref() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("first_login", true)
        editor.apply()
    }

}