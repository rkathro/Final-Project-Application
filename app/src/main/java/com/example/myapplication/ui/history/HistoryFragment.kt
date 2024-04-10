package com.example.myapplication.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HistoryFragment : Fragment() {
    data class HistoryItem(val companyName: String, val time: String, val logoResource: Int) {

    }
    private lateinit var adapter: HistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.historyRecyclerView)
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

    private fun createDummyData(): List<HistoryItem> {
        // Replace this with your actual data retrieval logic
        return listOf(
            HistoryItem("Charlotte", "10:00 AM", R.drawable.charlotte_49ers_1),
            HistoryItem("Wells Fargo", "11:30 AM", R.drawable.wells_fargo),
            HistoryItem("Facebook", "1:45 PM", R.drawable.facebook_3_2)
        )
    }
}