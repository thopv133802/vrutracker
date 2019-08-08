package com.thopham.projects.research.apiservice.core

import com.thopham.projects.research.apiservice.ApiServiceApplication
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
//                                    .trustManager(ApiServiceApplication::class.java.getResourceAsStream("/ssl/server.crt"))
//                                    .build()
//                    )
                    .usePlaintext()
                    .build()
        }
    }
}