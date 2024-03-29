package com.lambui09.mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lambui09.mvvm.R
import com.lambui09.mvvm.ui.base.fragment.BaseFragment
import com.lambui09.mvvm.ui.main.MainViewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel by appViewModels<HomeViewModel>()

    private val mainViewModel by appActivityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        textView.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_taskDetail)
        }
        return root
    }
}