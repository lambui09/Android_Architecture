package com.lambui09.mvvm.ui.task_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambui09.mvvm.R
import com.lambui09.mvvm.ui.base.fragment.BaseFragment

class TaskDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

}