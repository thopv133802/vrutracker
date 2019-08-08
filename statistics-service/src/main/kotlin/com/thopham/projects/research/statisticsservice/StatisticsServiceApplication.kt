package com.thopham.projects.research.statisticsservice

import com.thopham.projects.research.statisticsservice.services.StatisticsServiceGrpcImpl
import io.grpc.ServerBuilder
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.InetSocketAddress

@SpringBootApplication
class StatisticsServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            val context = runApplication<StatisticsServiceApplication>(*args)
            val statisticsServiceImpl = context.getBean(StatisticsServiceGrpcImpl::class.java)

            val server = NettyServerBuilder.forAddress(InetSocketAddress(18081))
//                    .useTransportSecurity(StatisticsServiceApplication::class.java.getResourceAsStream("/ssl/server.crt"), StatisticsServiceApplication::class.java.getResourceAsStream("/ssl/server.pem"))
                    .addService(statisticsServiceImpl)
                    .build()
                    .start()

            Runtime.getRuntime().addShutdownHook(Thread {
                server.shutdown()
            })

            server.awaitTermination()
        }
    }
}
