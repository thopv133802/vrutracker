package com.thopham.projects.research.userservice.services

import com.proto.user.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Component

@Component
class UserServiceGrpcImpl(private val userService: UserService): UserServiceGrpc.UserServiceImplBase() {
    override fun existsById(request: ExistsByIdRequest, responseObserver: StreamObserver<ExistsByIdResponse>) {
        try{
            val result = userService.existsById(request.userId)
            responseObserver.onNext(
                    ExistsByIdResponse.newBuilder()
                            .setExists(result)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch (exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription("UserService: existsById" + exception.message).asException())
        }
    }
    override fun login(request: LoginRequest, responseObserver: StreamObserver<LoginResponse>) {
        try{
            val result = userService.login(request.username, request.password)
            val user = result.user
            responseObserver.onNext(
                    LoginResponse.newBuilder()
                            .setUserId(user.id)
                            .setUsername(user.username)
                            .setEmail(user.email)
                            .setPhone(user.phone)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription(exception.message).asException())
        }
    }

    override fun register(request: RegisterRequest, responseObserver: StreamObserver<RegisterResponse>) {
        try{
            val result = userService.register(
                    username = request.username,
                    password = request.password,
                    email = request.email,
                    phone = request.phone
            )
            val user = result.user
            responseObserver.onNext(
                    RegisterResponse.newBuilder()
                            .setUserId(user.id)
                            .setUsername(user.username)
                            .setEmail(user.email)
                            .setPhone(user.phone)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription(exception.message).asException())
        }
    }
}