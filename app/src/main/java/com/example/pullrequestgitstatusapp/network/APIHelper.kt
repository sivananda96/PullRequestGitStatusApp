package com.example.pullrequestgitstatusapp.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIHelper @Inject
constructor(private val apiService: APIService) : NetworkAPIs {

    override fun getAPIService(): APIService {
        return apiService
    }
}
