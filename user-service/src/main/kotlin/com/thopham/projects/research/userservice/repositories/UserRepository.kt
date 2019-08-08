package com.thopham.projects.research.userservice.repositories

import com.thopham.projects.research.userservice.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity, Int>{
    fun findByUsername(username: String): Optional<UserEntity>
    fun existsByUsername(username: String): Boolean
    fun getOneByUsername(username: String): UserEntity
}
