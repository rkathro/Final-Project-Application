package com.example.myapplication.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HistoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView  // Declare recyclerView as a class-level property

    data class HistoryItem(val companyName: String, val time: String, val logoResource: Int)

    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        recyclerView = root.findViewById(R.id.historyRecyclerView) // Initialize recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val dataList = listOf(
            HistoryItem("Charlotte", "10:00 AM", R.drawable.charlotte_49ers_1),
            HistoryItem("Wells Fargo", "11:30 AM", R.drawable.wells_fargo),
            HistoryItem("Facebook", "1:45 PM", R.drawable.facebook_3_2)
        )
        adapter = HistoryAdapter(dataList.toMutableList())
        recyclerView.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the delete button and set onClickListener
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            // Get selected items from the adapter and delete them
            val selectedItems = adapter.getSelectedItems()
            (recyclerView.adapter as HistoryAdapter).removeItems(selectedItems)
        }
    }
}
