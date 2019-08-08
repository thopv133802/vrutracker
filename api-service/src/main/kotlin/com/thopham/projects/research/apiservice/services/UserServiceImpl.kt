package com.thopham.projects.research.apiservice.services

import com.proto.user.LoginRequest
import com.proto.user.RegisterRequest
import com.proto.user.UserServiceGrpc
import com.thopham.projects.research.apiservice.core.ChannelUtils
import com.thopham.projects.research.apiservice.models.UserModel
import org.springframework.stereotype.Component

data class LoginResult(
        val token: String
)
data class RegisterResult(
        val userId: Int
)

@Component
class UserServiceImpl(private val tokenGenerator: TokenGenerator){
    fun login(username: String, password: String): LoginResult {
        val channel = ChannelUtils.buildChannel("user-service", 18082)
        val stub = UserServiceGrpc.newBlockingStub(channel)
        val response = stub.login(
                LoginRequest.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .build()
        )
        channel.shutdown()
        val token = tokenGenerator.generate(
                UserModel(
                        response.userId,
                        response.username,
                        response.phone,
                        response.email
                )
        )
        return LoginResult(
                token.token
        )
    }

    fun register(username: String, password: String, email: String, phone: String): RegisterResult {
        val channel = ChannelUtils.buildChannel("user-service", 18082)
        val stub = UserServiceGrpc.newBlockingStub(channel)
        val response = stub.register(
                RegisterRequest.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .setEmail(email)
                        .setPhone(phone)
                        .build()
        )
        channel.shutdown()
        return RegisterResult(
                response.userId
        )
    }
}