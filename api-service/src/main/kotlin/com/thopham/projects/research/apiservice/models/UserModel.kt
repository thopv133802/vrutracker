package com.thopham.projects.research.apiservice.models

import com.fasterxml.jackson.annotation.JsonProperty
class UserModel(
        val id: Int,
        val username: String,
        val phone: String,
        val email: String
)