package com.example.pullrequestgitstatusapp.data.source.repository.remote

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.network.NetworkAPIs
import io.reactivex.Observable

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRemoteDataSource @Inject
constructor(private val networkAPIs: NetworkAPIs) : AppDataSource {

    override fun getPullRequests(ownerName: String, repoName: String,
                                 state: String, page: Int,
                                 sortBy: String, direction: String): Observable<List<PullRequest>> {
        return networkAPIs.getAPIService()
                .getGithubPullRequest(ownerName, repoName, state, page, sortBy, direction)
    }
}