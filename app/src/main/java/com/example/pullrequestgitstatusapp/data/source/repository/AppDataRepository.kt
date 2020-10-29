package com.example.pullrequestgitstatusapp.data.source.repository

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.di.Local
import com.example.pullrequestgitstatusapp.di.Remote
import io.reactivex.Observable

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataRepository @Inject
constructor(@param:Remote private val remoteAppDataSource: AppDataSource,
            @param:Local private val localAppDataSource: AppDataSource) : AppDataSource {

    override fun getPullRequests(ownerName: String, repoName: String,
                                 state: String, page: Int,
                                 sortBy: String, direction: String): Observable<List<PullRequest>> {
        return remoteAppDataSource.getPullRequests(ownerName, repoName, state, page, sortBy, direction)
    }
}
