package com.example.pullrequestgitstatusapp.base

import android.app.Application

import com.example.pullrequestgitstatusapp.di.component.ApplicationComponent
import com.example.pullrequestgitstatusapp.di.component.DaggerApplicationComponent
import com.example.pullrequestgitstatusapp.di.module.ApplicationModule
import com.example.pullrequestgitstatusapp.di.module.DataModule
import com.example.pullrequestgitstatusapp.di.module.NetworkModule
import com.example.pullrequestgitstatusapp.utils.AppLogger

/**
 * Entry place when application start
 * Good place to initialize stuff that has an Application Scope
 */

class MainApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataModule(DataModule())
            .networkModule(NetworkModule())
            .build()

        component.inject(this)

        instance = this

        (instance as MainApplication).initializeInstance()

    }

    // Here we do one-off initialisation which should apply to all activities
    // in the application.
    private fun initializeInstance() {
        //globally initialize the App Logger
        AppLogger.init()
    }

    companion object {

        // Anywhere in the application where an instance is required, this method
        // can be used to retrieve it.
        lateinit var instance: Application
    }

}
