package com.example.pullrequestgitstatusapp.ui.main.prlist

import android.os.Bundle
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.base.BaseActivity
import com.example.pullrequestgitstatusapp.utils.ext.findFragmentById
import com.example.pullrequestgitstatusapp.utils.ext.setFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: UserAccountFragment? = findFragmentById(R.id.fragmentContainer)
        if (fragment == null)
            setFragment(UserAccountFragment(), R.id.fragmentContainer)
    }
}

