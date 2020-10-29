package com.example.pullrequestgitstatusapp.data.source.repository

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.utils.AppConstants
import io.reactivex.Observable

interface AppDataSource {

    fun getPullRequests(ownerName: String,
                        repoName: String,
                        state: String,
                        page: Int = 1,
                        sortBy: String = AppConstants.SORT_BY_CREATED,
                        direction: String = AppConstants.SORT_ORDER_DESCENDING): Observable<List<PullRequest>>

}
