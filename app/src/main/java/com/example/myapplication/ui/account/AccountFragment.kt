package com.example.myapplication.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentAccountBinding
import com.example.myapplication.databinding.GrayBoxBinding


class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private var _grayBoxBinding: GrayBoxBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _grayBoxBinding = GrayBoxBinding.inflate(inflater,container,false)
        val grayBoxRoot = _grayBoxBinding!!.root
        (root as ViewGroup).addView(grayBoxRoot)
        val grayBoxTextView = _grayBoxBinding!!
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _grayBoxBinding = null
    }
}