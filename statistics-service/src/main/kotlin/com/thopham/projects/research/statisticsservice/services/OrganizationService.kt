package com.thopham.projects.research.statisticsservice.services

import com.proto.organization.BelongToRequest
import com.proto.organization.OrganizationServiceGrpc
import com.thopham.projects.research.statisticsservice.core.ChannelUtils
import org.springframework.stereotype.Component

@Component
class OrganizationService {
    fun belongTo(userId: Int, orgId: Int): Boolean {
        val channel = ChannelUtils.buildChannel("organization-service", 18083)
        val stub = OrganizationServiceGrpc.newBlockingStub(channel)
        val response = stub.belongTo(
                BelongToRequest.newBuilder()
                        .setOrgId(orgId)
                        .setUserId(userId)
                        .build()
        )
        return response.belongTo
    }
}