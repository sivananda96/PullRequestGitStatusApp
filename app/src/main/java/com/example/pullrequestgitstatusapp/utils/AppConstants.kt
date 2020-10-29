package com.example.pullrequestgitstatusapp.utils

/**
 * Constants that will be used through the app
 *
 * NOTE: use [JvmField] if using java with kotlin to avoid [AppConstants.INSTANCE.<name_access>]
 *
 */
object AppConstants {
    const val DB_NAME = "app.db"
    const val PREF_NAME = "app_pref"

    const val SORT_ORDER_DESCENDING = "desc"
    const val SORT_ORDER_ASCENDING = "asc"

    const val SORT_BY_CREATED = "created"
    const val SORT_BY_UPDATED = "updated"
    const val SORT_BY_POPULARITY = "popularity"

    const val PR_STATE_OPEN = "open"
    const val PR_STATE_CLOSED = "closed"
    const val PR_STATE_ALL = "all"

}
