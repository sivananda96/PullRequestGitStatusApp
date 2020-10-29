package com.example.pullrequestgitstatusapp.di.component

import android.app.Application
import android.content.Context

import com.example.pullrequestgitstatusapp.base.MainApplication
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataRepository
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.di.ApplicationContext
import com.example.pullrequestgitstatusapp.di.module.ApplicationModule
import com.example.pullrequestgitstatusapp.di.module.DataModule
import com.example.pullrequestgitstatusapp.di.module.NetworkModule

import javax.inject.Singleton

import dagger.Component


@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun getAppRepository(): AppDataSource

    fun inject(app: MainApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

}
