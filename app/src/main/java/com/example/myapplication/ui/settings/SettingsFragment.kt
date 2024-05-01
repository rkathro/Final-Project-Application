package com.example.myapplication.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnChangeUsername.setOnClickListener {
            showChangeUsernameDialog()
        }
        binding.btnChangePassword.setOnClickListener {
            showChangePasswordDialog()
        }
        binding.btnChangeEmail.setOnClickListener {
            showChangeEmailDialog()
        }
        binding.btnChangePhoneNumber.setOnClickListener {
            showChangePhoneNumberDialog()
        }
        binding.btnRetrainFacialRecognition.setOnClickListener {
            showRetrainFacialRecognitionDialog()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showChangeUsernameDialog() {
        val dialogView = layoutInflater.inflate(R.layout.change_username_dialog, null)
        val editTextEmail = dialogView.findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = dialogView.findViewById<EditText>(R.id.editTextPassword)
        val editTextNewUsername = dialogView.findViewById<EditText>(R.id.editTextNewUsername)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val newUsername = editTextNewUsername.text.toString()

            if (validateUsernameChange(email, password, newUsername)) {
                //password is correct change the vlaue stored in UserDataViewModel
                Toast.makeText(requireContext(), "Username changed successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun showChangePasswordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.change_password_dialog, null)
        val editTextCurrentPassword = dialogView.findViewById<EditText>(R.id.editTextCurrentPassword)
        val editTextNewPassword = dialogView.findViewById<EditText>(R.id.editTextNewPassword)
        val editTextConfirmNewPassword = dialogView.findViewById<EditText>(R.id.editTextConfirmPassword)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val currentPassword = editTextCurrentPassword.text.toString()
            val newPassword = editTextNewPassword.text.toString()
            val confirmNewPassword = editTextConfirmNewPassword.text.toString()

            if (validatePasswords(currentPassword, newPassword, confirmNewPassword)) {
                //password is correct change the vlaue stored in UserDataViewModel
                Toast.makeText(requireContext(), "Password changed successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Passwords do not match or are invalid", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun showChangeEmailDialog() {
        val dialogView = layoutInflater.inflate(R.layout.change_email_dialog, null)
        val editTextCurrentEmail = dialogView.findViewById<EditText>(R.id.editTextCurrentEmail)
        val editTextPassword = dialogView.findViewById<EditText>(R.id.editTextPassword)
        val editTextNewEmail = dialogView.findViewById<EditText>(R.id.editTextNewEmail)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val currentEmail = editTextCurrentEmail.text.toString()
            val password = editTextPassword.text.toString()
            val newEmail = editTextNewEmail.text.toString()

            if (validateEmailChange(currentEmail, password, newEmail)) {
                // Email change logic goes here
                Toast.makeText(requireContext(), "Email changed successfully", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun showChangePhoneNumberDialog() {
        val dialogView = layoutInflater.inflate(R.layout.change_phone_number_dialog, null)
        val editTextCurrentPhoneNumber = dialogView.findViewById<EditText>(R.id.editTextCurrentPhoneNumber)
        val editTextPassword = dialogView.findViewById<EditText>(R.id.editTextPassword)
        val editTextNewPhoneNumber = dialogView.findViewById<EditText>(R.id.editTextNewPhoneNumber)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val currentPhoneNumber = editTextCurrentPhoneNumber.text.toString()
            val password = editTextPassword.text.toString()
            val newPhoneNumber = editTextNewPhoneNumber.text.toString()

            if (validatePhoneNumberChange(currentPhoneNumber, password, newPhoneNumber)) {
                // Phone number change logic goes here
                Toast.makeText(requireContext(), "Phone number changed successfully", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(requireContext(), "Invalid phone number or password", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun showRetrainFacialRecognitionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.change_facial_rec_dialog, null)
        val editTextPassword = dialogView.findViewById<EditText>(R.id.editTextPassword)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val password = editTextPassword.text.toString()

            //if true print retraining facial rec and call however we decide to retrain
            if (validatePasswordForFacialRecognition(password)) {
                Toast.makeText(requireContext(), "Retraining facial recognition...", Toast.LENGTH_SHORT).show()
                //call retraining function here
            } else {
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }

    private fun validateUsernameChange(email: String, password: String, newUsername: String): Boolean {
        //change one currentPassword to the password stored in UserDataViewModel
        if(password == password && email == email && newUsername.isNotEmpty())
            return true
        else
            return false
    }
    private fun validatePasswords(currentPassword: String, newPassword: String, confirmNewPassword: String): Boolean {
        //change one currentPassword to the password stored in UserDataViewModel
        if(currentPassword == currentPassword && newPassword == confirmNewPassword && isPasswordValid(newPassword))
            return true
        else
            return false
    }
    private fun validateEmailChange(currentEmail: String, password: String, newEmail: String): Boolean {
        //change one password and current email to the password and email stored in UserDataViewModel
        if(password == password && currentEmail == currentEmail && isEmailValid(newEmail))
            return true
        else
            return false
    }
    private fun validatePhoneNumberChange(currentPhoneNumber: String, password: String, newPhoneNumber: String): Boolean {
        //change one password and current email to the password and email stored in UserDataViewModel
        if(password == password && currentPhoneNumber == currentPhoneNumber && isPhoneNumberValid(newPhoneNumber))
            return true
        else
            return false
    }
    private fun validatePasswordForFacialRecognition(password: String): Boolean {
        //replace password with password from db
        return password == password
    }
    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$".toRegex()

        return passwordPattern.matches(password)
    }
    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        /*var phonePattern = "^[0-9]{10}\$"
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
