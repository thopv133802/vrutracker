package com.thopham.projects.research.userservice

import com.thopham.projects.research.userservice.services.UserServiceGrpcImpl
import io.grpc.ServerBuilder
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.InetSocketAddress

@SpringBootApplication
class UserServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = runApplication<UserServiceApplication>(*args)
            val userServiceGrpcImpl = context.getBean(UserServiceGrpcImpl::class.java)
            val server = NettyServerBuilder.forAddress(InetSocketAddress(18082))
//                    .useTransportSecurity(UserServiceApplication::class.java.getResourceAsStream("/ssl/server.crt"), UserServiceApplication::class.java.getResourceAsStream("/ssl/server.pem"))
                    .addService(userServiceGrpcImpl)
                    .build()
                    .start()
            Runtime.getRuntime()
                    .addShutdownHook(Thread {
                        server.shutdown()
                    })
            server.awaitTermination()
        }
    }
}


