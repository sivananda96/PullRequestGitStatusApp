package com.example.pullrequestgitstatusapp.di.module

import com.example.pullrequestgitstatusapp.network.APIService
import com.example.pullrequestgitstatusapp.network.NetworkAPIs
import com.example.pullrequestgitstatusapp.network.NetworkUtils
import com.example.pullrequestgitstatusapp.network.APIHelper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(NetworkUtils.httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun providesNetworkService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    internal fun providesRetrofitHelper(apiHelper: APIHelper): NetworkAPIs {
        return apiHelper
    }

}
