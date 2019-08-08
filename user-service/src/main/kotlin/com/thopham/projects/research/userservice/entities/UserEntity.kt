package com.thopham.projects.research.userservice.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Column(unique = true)
        val username: String,
        val password: String,
        val phone: String,
        val email: String,
        val enabled: Boolean,
        val created: Long
){
    fun matches(password: String) : Boolean{
        println("""Compare: $password vs ${this.password}""")
        return this.password == password
    }

    constructor(): this(0, "", "", "", "", false, 0)
    companion object{
        fun newInstance(username: String, password: String, phone: String, email: String): UserEntity{
            return UserEntity(id = 0, username = username, password = password, phone = phone, email = email, enabled = true, created = System.currentTimeMillis())
        }
    }
}