package com.example.myapplication.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentAccountBinding
import com.example.myapplication.databinding.GrayBoxBinding
import AccountAdapter
import com.example.myapplication.R


class AccountFragment : Fragment() {
    data class CompanyData(val logoDrawableId: Int, val companyName: String) {

    }

    private var _binding: FragmentAccountBinding? = null
    private var _grayBoxBinding: GrayBoxBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AccountAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        val dataList = listOf(
            CompanyData(R.drawable.charlotte_49ers_1, "Charlotte"),
            CompanyData(R.drawable.wells_fargo, "Wells Fargo"),
            CompanyData(R.drawable.facebook_3_2, "Facebook")
        )
        adapter = AccountAdapter(dataList.toMutableList())
        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _grayBoxBinding = null
    }
}