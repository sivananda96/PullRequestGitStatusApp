package com.example.pullrequestgitstatusapp.utils

import android.util.Log

import com.example.pullrequestgitstatusapp.BuildConfig

/**
 * An App logger to only log in Debug mode
 */

object AppLogger {

    var isDebugMode = true

    fun init() {
        if (!BuildConfig.DEBUG) {
            isDebugMode = false
        }
    }

    // For Debug Messages
    fun d(tag: String, msg: String) {
        if (isDebugMode)
            Log.d(tag, msg)
    }

    //For Error Messages
    fun e(tag: String, msg: String) {
        if (isDebugMode)
            Log.e(tag, msg)
    }

    //For Warning Messages
    fun w(tag: String, msg: String) {
        if (isDebugMode)
            Log.w(tag, msg)
    }

    //For Information Messages
    fun i(tag: String, msg: String) {
        if (isDebugMode)
            Log.i(tag, msg)
    }

    //For Verbose Messages
    fun v(tag: String, msg: String) {
        if (isDebugMode)
            Log.v(tag, msg)
    }

}
