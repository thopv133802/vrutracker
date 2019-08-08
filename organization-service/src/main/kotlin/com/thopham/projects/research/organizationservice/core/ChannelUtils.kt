package com.thopham.projects.research.organizationservice.core

import com.thopham.projects.research.organizationservice.OrganizationServiceApplication
import io.grpc.ManagedChannel
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder

class ChannelUtils {
    companion object {
        fun buildChannel(host: String, port: Int) : ManagedChannel {
            return NettyChannelBuilder.forAddress(host, port)
//                    .useTransportSecurity()
//                    .sslContext(
//                            GrpcSslContexts.forClient()
//                                    .trustManager(ChannelUtils::class.java.getResourceAsStream("/ssl/server.crt"))
//                                    .build()
//                    )
                    .usePlaintext()
                    .build()
        }
    }
}