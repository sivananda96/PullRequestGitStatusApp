package com.example.pullrequestgitstatusapp.data.source.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pullrequestgitstatusapp.data.models.PullRequest

/**
 * The Room Database that contains the Item table.
 */

@Database(entities = [PullRequest::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}