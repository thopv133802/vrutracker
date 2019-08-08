package com.thopham.projects.research.organizationservice.entities

import com.proto.organization.OrganizationProtobuf
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OrganizationEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val name: String,
        val userId: Int,
        val created: Long
){
    constructor(): this(0, "", 0, 0)
    companion object{
        fun newInstance(name: String, userId: Int): OrganizationEntity{
            return OrganizationEntity(0, name, userId, System.currentTimeMillis())
        }
    }
    fun toProtobuf(): OrganizationProtobuf {
        return OrganizationProtobuf.newBuilder()
                .setOrgId(id)
                .setName(name)
                .build()
    }
}