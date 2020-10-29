package com.example.pullrequestgitstatusapp.data.models

import java.io.Serializable

data class User(
        val id: String,
        val login: String,
        val url: String) : Serializable