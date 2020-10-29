package com.example.pullrequestgitstatusapp.di.module

import com.example.pullrequestgitstatusapp.data.source.db.AppDatabase
import com.example.pullrequestgitstatusapp.data.source.db.AppDbOpenHelper
import com.example.pullrequestgitstatusapp.data.source.repository.local.AppLocalDataSource
import com.example.pullrequestgitstatusapp.data.source.repository.remote.AppRemoteDataSource
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataRepository
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import com.example.pullrequestgitstatusapp.di.DatabaseInfo
import com.example.pullrequestgitstatusapp.di.Local
import com.example.pullrequestgitstatusapp.di.Remote
import com.example.pullrequestgitstatusapp.utils.AppConstants

import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    @Singleton
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    @Local
    internal fun provideAppLocalDataSource(appLocalDataSource: AppLocalDataSource): AppDataSource {
        return appLocalDataSource
    }

    @Provides
    @Singleton
    @Remote
    internal fun provideAppRemoteDataSource(appRemoteDataSource: AppRemoteDataSource): AppDataSource {
        return appRemoteDataSource
    }

    @Provides
    @Singleton
    internal fun provideAppRepository(dataRepository: AppDataRepository): AppDataSource {
        return dataRepository
    }

    @Provides
    @Singleton
    internal fun provideAppDb(appDbOpenHelper: AppDbOpenHelper): AppDatabase {
        return appDbOpenHelper.database
    }


}
