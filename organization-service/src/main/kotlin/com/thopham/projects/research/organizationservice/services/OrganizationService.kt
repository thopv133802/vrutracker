package com.thopham.projects.research.organizationservice.services

import com.thopham.projects.research.organizationservice.entities.OrganizationEntity
import com.thopham.projects.research.organizationservice.repositories.OrganizationRepository
import org.springframework.stereotype.Component

data class ListOrganizationResult(
        val organizations: List<OrganizationEntity>
)
data class CreateOrganizationResult(
        val organization: OrganizationEntity
)

interface OrganizationService {
    fun fetchOrganizations(userId: Int): ListOrganizationResult
    fun createOrganization(userId: Int, name: String): CreateOrganizationResult
    fun belongTo(userId: Int, orgId: Int): Boolean
}
@Component
class OrganizationServiceImpl(val organizationRepository: OrganizationRepository, val userService: UserService): OrganizationService {
    override fun belongTo(userId: Int, orgId: Int): Boolean {
        val organizationDoesNotExists = !organizationRepository.existsById(orgId)
        if (organizationDoesNotExists)
            throw Exception("Org không tồn tại.")
        val organization = organizationRepository.getOne(orgId)
        return organization.userId  == userId
    }

    override fun createOrganization(userId: Int, name: String): CreateOrganizationResult {
        val userDoesNotExists = !userService.existsById(userId)
        if (userDoesNotExists)
            throw Exception("Người dùng không tồn tại.")
        val organization = organizationRepository.save(OrganizationEntity.newInstance(name, userId))
        return CreateOrganizationResult(
                organization
        )
    }

    override fun fetchOrganizations(userId: Int): ListOrganizationResult {
        return ListOrganizationResult(
                organizationRepository.findAllByUserId(userId)
        )
    }
}