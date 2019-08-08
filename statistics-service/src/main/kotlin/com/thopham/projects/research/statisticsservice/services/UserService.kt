package com.thopham.projects.research.statisticsservice.services

import com.proto.user.ExistsByIdRequest
import com.proto.user.UserServiceGrpc
import com.thopham.projects.research.statisticsservice.core.ChannelUtils
import org.springframework.stereotype.Component

@Component
class UserService {
    fun existsById(userId: Int): Boolean {
        val channel = ChannelUtils.buildChannel("user-service", 18082)
        val stub = UserServiceGrpc.newBlockingStub(channel)
        return stub.existsById(
                ExistsByIdRequest.newBuilder()
                        .setUserId(userId)
                        .build()
        ).exists
    }
}