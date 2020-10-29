package com.example.pullrequestgitstatusapp.di.module

import android.app.Application
import android.content.Context

import com.example.pullrequestgitstatusapp.di.ApplicationContext

import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

}
