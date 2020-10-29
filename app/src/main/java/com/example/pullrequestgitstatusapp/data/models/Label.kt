package com.example.pullrequestgitstatusapp.data.models

import java.io.Serializable

data class Label(
        val id: String,
        val url: String,
        val name: String,
        val color: String,
        val default: Boolean = false) : Serializable