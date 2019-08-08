package com.thopham.projects.research.organizationservice.services

import com.proto.organization.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Component

@Component
class OrganizationServiceGrpcImpl(private val organizationService: OrganizationService): OrganizationServiceGrpc.OrganizationServiceImplBase() {
    override fun belongTo(request: BelongToRequest, responseObserver: StreamObserver<BelongToResponse>) {
        try{
            val result = organizationService.belongTo(request.userId, request.orgId)
            responseObserver.onNext(
                    BelongToResponse.newBuilder()
                            .setBelongTo(result)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription(exception.message).asException())
        }
    }
    override fun createOrganization(request: CreateOrganizationRequest, responseObserver: StreamObserver<CreateOrganizationResponse>) {
        try{
            val userId = request.userId
            val result = organizationService.createOrganization(userId, request.name)
            responseObserver.onNext(
                    CreateOrganizationResponse.newBuilder()
                            .setOrgId(result.organization.id)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription(exception.message).asException())
        }
    }

    override fun listOrganizations(request: ListOrganizationRequest, responseObserver: StreamObserver<ListOrganizationResponse>) {
        try{
            val userId = request.userId
            val result = organizationService.fetchOrganizations(userId)
            responseObserver.onNext(
                    ListOrganizationResponse.newBuilder()
                            .addAllOrganization(
                                    result.organizations.map{entity -> entity.toProtobuf()}
                            )
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(Status.INTERNAL.withDescription(exception.message).asException())
        }
    }
}