package com.example.pullrequestgitstatusapp.network

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("/repos/{owner}/{repo}/pulls")
    fun getGithubPullRequest(@Path("owner") ownerName: String,
                             @Path("repo") repoName: String,
                             @Query("state") state: String,
                             @Query("page") page: Int,
                             @Query("sort") sortBy: String,
                             @Query("direction") direction: String): Observable<List<PullRequest>>

}
