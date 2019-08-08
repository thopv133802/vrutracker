package com.thopham.projects.research.userservice.services

import com.thopham.projects.research.userservice.entities.UserEntity
import com.thopham.projects.research.userservice.repositories.UserRepository
import org.springframework.stereotype.Component

data class LoginResult(
        val user: UserEntity
)
data class RegisterResult(
        val user: UserEntity
)

interface UserService {
    fun login(username: String, password: String): LoginResult
    fun register(username: String, password: String, email: String, phone: String): RegisterResult
    fun existsById(userId: Int): Boolean
}

@Component
class UserServiceImpl(private val userRepository: UserRepository): UserService{
    override fun existsById(userId: Int): Boolean {
        return userRepository.existsById(userId)
    }

    override fun login(username: String, password: String): LoginResult {
        val userDoesNotExists = !userRepository.existsByUsername(username)
        if (userDoesNotExists)
            throw Exception("Người dùng không tồn tại.")
        val user = userRepository.getOneByUsername(username)
        println(user)
        val passwordDoesNotMatch = !user.matches(password)
        if (passwordDoesNotMatch)
            throw Exception("Mật khẩu không trùng khớp.")
        return LoginResult(
                user
        )
    }

    override fun register(username: String, password: String, email: String, phone: String): RegisterResult {
        val userExists = userRepository.existsByUsername(username)
        if (userExists)
            throw Exception("Người dùng đã tồn tại.")
        val user = userRepository.save(UserEntity.newInstance(
                username = username,
                password = password,
                email = email,
                phone = phone
        ))
        return RegisterResult(
                user
        )
    }

}