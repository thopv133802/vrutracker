package com.thopham.projects.research.organizationservice.repositories

import com.thopham.projects.research.organizationservice.entities.OrganizationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository: JpaRepository<OrganizationEntity, Int>{
    fun findAllByUserId(userId: Int): List<OrganizationEntity>
}