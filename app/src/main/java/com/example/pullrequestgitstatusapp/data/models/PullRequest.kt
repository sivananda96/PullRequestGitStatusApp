
package com.example.pullrequestgitstatusapp.data.models

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "item")
data class PullRequest(
        @field:PrimaryKey
        val id: String,
        val url: String,
        val html_url: String,
        val number: Int,
        val state: String,
        val locked: Boolean = false,
        val title: String,
        val body: String,
        val user: User,
        val labels: List<Label>,
        val created_at: Date,
        val update_at: Date) : Serializable {

    fun getAgoTime(): String {
        return DateUtils.getRelativeTimeSpanString(created_at.time).toString()
    }
}