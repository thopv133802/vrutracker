package com.thopham.projects.research.apiservice.services

import com.proto.organization.CreateOrganizationRequest
import com.proto.organization.ListOrganizationRequest
import com.proto.organization.OrganizationServiceGrpc
import com.thopham.projects.research.apiservice.core.ChannelUtils
import com.thopham.projects.research.apiservice.models.OrganizationModel
import org.springframework.stereotype.Component


data class ListOrganizationResult(
        val organizations: List<OrganizationModel>
)
data class CreateOrganizationResult(
        val orgId: Int
)

@Component
class OrganizationServiceImpl(val tokenGenerator: TokenGenerator) {
    fun fetchOrganizations(token: String): ListOrganizationResult {
        val user = tokenGenerator.resolve(token)
        val channel = ChannelUtils.buildChannel("organization-service", 18083)
        val stub = OrganizationServiceGrpc.newBlockingStub(channel)
        val response = stub.listOrganizations(
                ListOrganizationRequest.newBuilder()
                        .setUserId(user.id)
                        .build()
        )
        channel.shutdown()
        return ListOrganizationResult(
                response.organizationList.map { organization ->
                    OrganizationModel(
                            organization.orgId,
                            organization.name
                    )
                }
        )
    }

    fun createOrganization(token: String, name: String): CreateOrganizationResult {
        val user = tokenGenerator.resolve(token)
        val channel = ChannelUtils.buildChannel("organization-service", 18083)
        val stub = OrganizationServiceGrpc.newBlockingStub(channel)
        val response = stub.createOrganization(
                CreateOrganizationRequest.newBuilder()
                        .setUserId(user.id)
                        .setName(name)
                        .build()
        )
        channel.shutdown()
        return CreateOrganizationResult(
                response.orgId
        )
    }
}