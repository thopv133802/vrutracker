package com.thopham.projects.research.organizationservice

import com.thopham.projects.research.organizationservice.services.OrganizationServiceGrpcImpl
import io.grpc.ServerBuilder
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.InetSocketAddress

@SpringBootApplication
class OrganizationServiceApplication{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = runApplication<OrganizationServiceApplication>(*args)
            val organizationServiceGrpcImpl = context.getBean(OrganizationServiceGrpcImpl::class.java)

            val server = NettyServerBuilder.forAddress(InetSocketAddress(18083))
//                    .useTransportSecurity(OrganizationServiceApplication::class.java.getResourceAsStream("/ssl/server.crt"), OrganizationServiceApplication::class.java.getResourceAsStream("/ssl/server.pem"))
                    .addService(organizationServiceGrpcImpl)
                    .build()
                    .start()

            Runtime.getRuntime().addShutdownHook(Thread {
                server.shutdown()
            })

            server.awaitTermination()
        }
    }
}


