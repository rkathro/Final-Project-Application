package com.example.myapplication.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentAccountBinding
import com.example.myapplication.databinding.GrayBoxBinding
import com.example.myapplication.R


class AccountFragment : Fragment() {
    data class CompanyData(val logoDrawableId: Int, val companyName: String, val companyPassword: String) {

    }

    private var _binding: FragmentAccountBinding? = null
    private var _grayBoxBinding: GrayBoxBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AccountAdapter
    private lateinit var dataList: MutableList<CompanyData>
    private lateinit var companyListPasswords: List<CompanyData>
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = root.findViewById(R.id.accountRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample data for testing
        companyListPasswords = listOf(
            CompanyData(R.drawable.charlotte_49ers_1, "Charlotte", "fortyniners"),
            CompanyData(R.drawable.wells_fargo, "Wells Fargo", "2013"),
            CompanyData(R.drawable.facebook_3_2, "Facebook", "zuckerberg"),
            CompanyData(R.drawable.gmail_svgrepo_com, "Gmail", "google")
        )
        dataList = mutableListOf(
            CompanyData(R.drawable.charlotte_49ers_1, "Charlotte", "fortyniners"),
            CompanyData(R.drawable.wells_fargo, "Wells Fargo", "2013"),
            CompanyData(R.drawable.facebook_3_2, "Facebook", "zuckerberg")

        )

        adapter = AccountAdapter(dataList)
        recyclerView.adapter = adapter

        binding.addAccount.setOnClickListener {
            addAccount()
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _grayBoxBinding = null
    }
    private fun addAccount() {
        val dialogView = layoutInflater.inflate(R.layout.change_facial_rec_dialog, null)
        val editTextPassword = dialogView.findViewById<EditText>(R.id.editTextPassword)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        btnSave.setOnClickListener {
            val password = editTextPassword.text.toString()

            if (validatePassword(password)) {
                for (i in companyListPasswords){
                    val companyPass = i.companyPassword
                    if(companyPass == password){
                        dataList.add(i)
                    }
                }
                adapter = AccountAdapter(dataList)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
            }
            alertDialog.dismiss()
        }
    }
    private fun validatePassword(password: String): Boolean {
        //check if password matches the organizations password
        for (i in companyListPasswords){
            val companyPass = i.companyPassword
            for(x in dataList) {
                val userPass = x.companyPassword
                if (companyPass == userPass) {
                    Toast.makeText(requireContext(), "Account already exists", Toast.LENGTH_SHORT)
                        .show()
                    return false
                } else if (companyPass == password) {
                    return true
                }
            }
        }
        return false
    }
}