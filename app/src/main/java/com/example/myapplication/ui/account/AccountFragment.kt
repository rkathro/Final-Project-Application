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
import android.util.Log
import androidx.appcompat.widget.PopupMenu
import com.example.myapplication.R

class AccountFragment : Fragment(), AccountAdapter.OnOptionClickListener {
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

        adapter = AccountAdapter(dataList, this)
        recyclerView.adapter = adapter

        adapter.setOnMenuClickListener { view, position ->
            showPopupMenu(view)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _grayBoxBinding = null
    }
    override fun onRenameClicked() {
        Log.d("AccountFragment", "Rename option clicked")
    }

    override fun onDeleteClicked() {
        Log.d("AccountFragment", "Delete option clicked")
    }

    override fun onCancelClicked() {
        Log.d("AccountFragment", "Cancel option clicked")
    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.options_menu) // Assuming you have defined a menu resource file named options_menu.xml
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_rename -> {
                    // Handle rename action
                    true
                }
                R.id.menu_delete -> {
                    // Handle delete action
                    true
                }
                R.id.menu_cancel -> {
                    // Handle cancel action
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}