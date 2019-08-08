package com.thopham.projects.research.apiservice.core

data class Response<T>(
        val status: Boolean,
        val message: String,
        val payload: T?
)