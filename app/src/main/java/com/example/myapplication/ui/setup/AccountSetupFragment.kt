package com.example.myapplication.ui.setup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.myapplication.R
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myapplication.ui.account.AccountFragment


class AccountSetupFragment() : Fragment() {

    private lateinit var viewModel: UserDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_setup, container, false)
        // Bind ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(UserDataViewModel::class.java)
        val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
        // Set up UI elements and listeners
        val editTextUsername = view.findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = view.findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = view.findViewById<EditText>(R.id.editTextConfirmPassword)
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val editTextPhoneNumber = view.findViewById<EditText>(R.id.editTextPhoneNumber)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()
            val email = editTextEmail.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            // Update first login value
            saveFirstLoginStatus()

            /* check user data
            if(username.isNotEmpty()){
                if(password == confirmPassword) {
                    if(isPasswordValid(password)) {
                        if (isEmailValid(email)) {
                            if (isPhoneNumberValid(phoneNumber)) {
                                viewModel.setUserData(username, password, email, phoneNumber)
                                navController.navigate(R.id.navigation_training)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Please enter a valid phone number",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Please enter a valid email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else{
                        Toast.makeText(requireContext(), "Please ensure password has atleast 8 chars, one number, one uppercase, and one lowercase letter", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Please enter a username", Toast.LENGTH_SHORT).show()
            }*/
            viewModel.setUserData(username, password, email, phoneNumber)
            navController.navigate(R.id.navigation_training)
        }

        return view
    }
    private fun saveFirstLoginStatus() {
        // Update first login status to false in SharedPreferences
        val editor = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE).edit()
        editor.putBoolean("first_login", false)
        editor.apply()
    }
    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$".toRegex()

        return passwordPattern.matches(password)
    }
    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        /*A more fun check that allows phone numbers to have - between nums
        might be more trouble than its worth though

        var phonePattern = "^[0-9]{10}\$"
        if(phoneNumber.matches(Regex(phonePattern))){
            return true
        }
        else{
            phonePattern = "^(\\d{3}-)?\\d{3}-\\d{4}\$"
            return phoneNumber.matches(Regex(phonePattern))
        }*/
        val phonePattern = "^[0-9]{10}\$"
        return phoneNumber.matches(Regex(phonePattern))
    }
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(Regex(emailPattern))
    }
}
