
package com.example.pullrequestgitstatusapp.data.source.db

import androidx.room.*
import com.example.pullrequestgitstatusapp.data.models.PullRequest

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleItem(pullRequest: PullRequest)

    @Query("SELECT * FROM Item WHERE id = :itemId")
    fun fetchItemByItemId(itemId: Int): PullRequest

    @Delete
    fun deleteItem(pullRequest: PullRequest)

}