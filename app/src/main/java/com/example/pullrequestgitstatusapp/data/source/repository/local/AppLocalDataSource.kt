package com.example.pullrequestgitstatusapp.data.source.repository.local

import com.example.pullrequestgitstatusapp.data.models.PullRequest
import com.example.pullrequestgitstatusapp.data.source.db.AppDatabase
import com.example.pullrequestgitstatusapp.data.source.db.ItemDao
import com.example.pullrequestgitstatusapp.data.source.repository.AppDataSource
import io.reactivex.Observable

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLocalDataSource @Inject
constructor(database: AppDatabase) : AppDataSource {

    private val itemDao: ItemDao = database.itemDao()

    override fun getPullRequests(ownerName: String, repoName: String,
                                 state: String, page: Int,
                                 sortBy: String, direction: String): Observable<List<PullRequest>> {
        // return itemDao.fetchItems()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}