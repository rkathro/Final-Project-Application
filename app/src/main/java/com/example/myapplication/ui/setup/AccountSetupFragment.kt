package com.example.myapplication.ui.setup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.myapplication.R
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myapplication.ui.account.AccountFragment


class AccountSetupFragment(private var navControllerHere: NavController? = null) : Fragment() {

    private lateinit var viewModel: UserDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_setup, container, false)
        // Bind ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(UserDataViewModel::class.java)
        //navControllerHere = findNavController(R.id.nav_host_fragment_activity_main)
        // Set up UI elements and listeners
        val editTextUsername = view.findViewById<EditText>(R.id.editTextUsername)
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val editTextPhoneNumber = view.findViewById<EditText>(R.id.editTextPhoneNumber)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            // Get user input
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            // Store user data in ViewModel
            viewModel.setUserData(username, email, phoneNumber)

            // Update first login status in SharedPreferences
            saveFirstLoginStatus()

            // Navigate to the account page
            navControllerHere?.navigate(R.id.navigation_account)
        }

        return view
    }


    private fun saveFirstLoginStatus() {
        // Update first login status to false in SharedPreferences
        val editor = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE).edit()
        editor.putBoolean("first_login", false)
        editor.apply()
    }
}
