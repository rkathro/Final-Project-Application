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
                // Passwords are not valid, show error message
                Toast.makeText(requireContext(), "Passwords do not match or are invalid", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun validatePasswords(currentPassword: String, newPassword: String, confirmNewPassword: String): Boolean {
        //change one currentPassword to the password stored in UserDataViewModel
        if(currentPassword == currentPassword)
            return newPassword == confirmNewPassword && newPassword.isNotEmpty() && currentPassword.isNotEmpty()
        else
            return false
    }
}
