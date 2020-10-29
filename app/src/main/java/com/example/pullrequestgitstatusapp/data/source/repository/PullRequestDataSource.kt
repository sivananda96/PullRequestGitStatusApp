
package com.example.pullrequestgitstatusapp.data.source.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.data.source.state.LoadingState
import io.reactivex.disposables.CompositeDisposable

class PullRequestDataSource constructor(
        private val owner: String,
        private val repo: String,
        private val state: String,
        private val appDataSource: AppDataSource,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, PullRequest>() {

    val loadingState: MutableLiveData<LoadingState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PullRequest>) {
        if (owner.isEmpty() || repo.isEmpty()) return

        loadingState.postValue(LoadingState.FIRST_LOADING)
        val disposable = appDataSource.getPullRequests(owner, repo, state, 1)
                .subscribe({
                    if (!it.isNullOrEmpty()) {
                        loadingState.postValue(LoadingState.FIRST_LOADED)
                        callback.onResult(it, null, 2)
                    } else
                        loadingState.postValue(LoadingState.FIRST_EMPTY)
                }, {
                    loadingState.postValue(LoadingState.FIRST_FAILED)
                })
        compositeDisposable.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
        if (owner.isEmpty() || repo.isEmpty()) return

        loadingState.postValue(LoadingState.LOADING)
        val disposable = appDataSource.getPullRequests(owner, repo, state, params.key)
                .subscribe({
                    loadingState.postValue(LoadingState.LOADED)

                    //if, calculated page and current page are same,return null to stop fetching data
                    //else, get the next page
                    val isPaginationEnd = it.isNullOrEmpty()
                    val nextKey = (if (isPaginationEnd) null else params.key + 1)

                    callback.onResult(it, nextKey)
                }, {
                    loadingState.postValue(LoadingState.FAILED)
                })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
        //do something
    }

    fun reset() {
        loadingState.postValue(LoadingState.RESET)
        compositeDisposable.clear()
        invalidate()
    }

}
