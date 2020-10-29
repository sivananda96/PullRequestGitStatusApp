package com.example.pullrequestgitstatusapp.ui.main.prlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.ui.main.prlist.PRListFragment


class UserAccountFragment : Fragment() {

    lateinit var etGithubOwnerName: EditText
    lateinit var etRepoName: EditText
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etGithubOwnerName = view.findViewById(R.id.etGitHubOwnerName)
        etRepoName = view.findViewById(R.id.etGitHubRepoName)
        btnSubmit = view.findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            var githubInfo = "${etGithubOwnerName.text}/${etRepoName.text}"
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PRListFragment.newInstance(githubInfo))
                .addToBackStack(null).commit()
        }
    }
}