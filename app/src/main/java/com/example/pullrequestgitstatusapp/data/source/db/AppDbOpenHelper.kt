package com.example.pullrequestgitstatusapp.data.source.db

import android.content.Context
import androidx.room.Room

import com.example.pullrequestgitstatusapp.di.ApplicationContext
import com.example.pullrequestgitstatusapp.di.DatabaseInfo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbOpenHelper @Inject
constructor(@ApplicationContext context: Context, @DatabaseInfo name: String) {
    val database: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, name)
            .build()
}
