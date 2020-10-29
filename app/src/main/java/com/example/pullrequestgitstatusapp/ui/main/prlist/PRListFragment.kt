package com.example.pullrequestgitstatusapp.ui.main.prlist

import android.content.Intent

import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.base.BaseFragment
import com.example.pullrequestgitstatusapp.data.source.state.LoadingStatus
import com.example.pullrequestgitstatusapp.databinding.FragmentPrListBinding
import com.example.pullrequestgitstatusapp.di.factory.AppViewModelFactory
import com.example.pullrequestgitstatusapp.ui.adapter.PRItemListAdapter
import com.example.pullrequestgitstatusapp.utils.AppLogger
import com.example.pullrequestgitstatusapp.utils.ext.getViewModel
import com.example.pullrequestgitstatusapp.utils.ext.hideKeyboard


import java.lang.Exception

import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class PRListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private lateinit var binding: FragmentPrListBinding
    private lateinit var prListViewModel: PRListViewModel

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pr_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //inject dependencies
        activityComponent.inject(this)

        //create the adapter
        val adapter = PRItemListAdapter {
            prListViewModel.onPullRequestItemClicked(it)
        }
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.itemRecyclerView.adapter = adapter

        //subscribe ui
        subscribeUi(adapter)

        prListViewModel.search(param1)
    }

    private fun subscribeUi(adapter: PRItemListAdapter) {
        prListViewModel = getViewModel(PRListViewModel::class.java, viewModelFactory)
        binding.hasItems = false
        binding.viewModel = prListViewModel

        prListViewModel.getErrorMsg().observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) onError(errorMessage)
        })
        prListViewModel.toastMsg.observe(viewLifecycleOwner, Observer {
            if (it != null) showToastMessage(it)
        })
        prListViewModel.pullRequestList.observe(viewLifecycleOwner, Observer { items ->
            val hasItems = (items != null && items.isNotEmpty())
            binding.hasItems = hasItems
            adapter.submitList(items)
        })
        prListViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it?.loadingStatus) {
                LoadingStatus.FIRST_RUNNING -> showProgress(true)
                LoadingStatus.FIRST_EMPTY -> {
                    showStatusMessage(getString(R.string.search_msg_repo_not_found))
                    showProgress(false)
                }
                LoadingStatus.FIRST_SUCCESS -> showProgress(false)
                LoadingStatus.FIRST_FAILED -> {
                    showStatusMessage(getString(R.string.search_msg_repo_not_found))
                    showProgress(false)
                }
                LoadingStatus.RUNNING -> showNextLoadProgress(true)
                LoadingStatus.SUCCESS -> showNextLoadProgress(false)
                LoadingStatus.FAILED -> {
                    showNextLoadProgress(false)
                    showToastMessage(R.string.error_cannot_load_more)
                }
                else -> showProgress(false)
            }
        })
        prListViewModel.pullRequestSelected.observe(viewLifecycleOwner, Observer {
            val url = it?.getContentIfNotHandled()
            if (!url.isNullOrEmpty())
                launchPullRequestUrl(url)
        })
    }

    private fun launchPullRequestUrl(url: String) {
        var finalUrl: String = url
        if (!url.startsWith("https://") && !url.startsWith("http://"))
            finalUrl = "http://$url"

        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
            startActivity(browserIntent)
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error occurred while launching...")
        }

    }

    private fun showStatusMessage(status: String) {
        Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
    }

    private fun showProgress(status: Boolean?) {

    }

    private fun showNextLoadProgress(status: Boolean?) {

    }

    companion object {
        const val TAG = "PRListFragment"

        fun newInstance(param1: String): PRListFragment =
            PRListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

    }
}
