package com.example.pullrequestgitstatusapp.ui.main.prlist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pullrequestgitstatusapp.R
import com.example.pullrequestgitstatusapp.base.BaseViewModel
import com.example.pullrequestgitstatusapp.data.source.factory.PullRequestDataFactory
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.data.source.state.LoadingState
import com.example.pullrequestgitstatusapp.utils.AppConstants
import com.example.pullrequestgitstatusapp.utils.GeneralUtils
import com.example.pullrequestgitstatusapp.utils.lifecycle.EventOnce
import com.example.pullrequestgitstatusapp.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * ViewModel for the [PRListViewModel] screen.
 * The ViewModel works with the [AppDataSource] to get the data.
 */
class PRListViewModel @Inject
constructor(appRepository: AppDataSource,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) :
        BaseViewModel(appRepository, schedulerProvider, compositeDisposable) {

    companion object {
        const val PAGE_SIZE = 10
    }

    val toastMsg: MutableLiveData<Int> = MutableLiveData()

    val pullRequestSelected: MutableLiveData<EventOnce<String>> = MutableLiveData()

    private var prevQuery: String? = null

    private val pullRequestDataFactory = PullRequestDataFactory(appRepository, compositeDisposable)

    val loadingState: LiveData<LoadingState> = Transformations.switchMap(pullRequestDataFactory.mutableLiveData) {
        it.loadingState
    }

    val pullRequestList by lazy {
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE).build()

        LivePagedListBuilder(pullRequestDataFactory, pagedListConfig)
                .setFetchExecutor(Executors.newFixedThreadPool(2))
                .build()
    }


    /**
     * Search for open pull request based on the [query]
     */
    fun search(query: String?) {

        //if the query is empty
        if (query.isNullOrEmpty()) {
            toastMsg.value = R.string.search_query_empty
            return
        }

        //if prev query is same as current, don't search
        if (prevQuery.equals(query))
            return

        //parse the search query to get the owner and repo, and check for validity
        val parseResult = GeneralUtils.parseSearchResult(query)
        if (parseResult.size != 2 || (parseResult[0].isEmpty() || parseResult[1].isEmpty())) {
            toastMsg.value = R.string.search_query_invalid
            return
        }

        //change the previous query
        prevQuery = query

        //update the search query and the result
        pullRequestDataFactory.setSearchQuery(parseResult[0].trim(), parseResult[1].trim(), AppConstants.PR_STATE_OPEN)
    }

    /**
     * Take action on Pull request item pressed
     */
    fun onPullRequestItemClicked(url: String?) {
        if (url.isNullOrEmpty()) return
        pullRequestSelected.value = EventOnce(url)
    }

}