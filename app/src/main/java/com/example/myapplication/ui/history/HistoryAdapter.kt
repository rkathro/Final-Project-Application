package com.example.myapplication.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HistoryAdapter(private val historyList: MutableList<HistoryFragment.HistoryItem>) : RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.bind(historyItem)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
    private val companyName: TextView = itemView.findViewById(R.id.companyName)
    private val time: TextView = itemView.findViewById(R.id.time)

    fun bind(historyItem: HistoryFragment.HistoryItem) {
        checkBox.isChecked = false // Set the initial state of the checkbox
        companyLogo.setImageResource(historyItem.logoResource)
        companyName.text = historyItem.companyName
        time.text = historyItem.time
    }
}