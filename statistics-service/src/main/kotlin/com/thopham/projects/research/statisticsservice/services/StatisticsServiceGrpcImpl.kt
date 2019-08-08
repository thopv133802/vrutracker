package com.thopham.projects.research.statisticsservice.services

import com.proto.statistics.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Component

@Component
class StatisticsServiceGrpcImpl(private val statisticsService: StatisticsService): StatisticsServiceGrpc.StatisticsServiceImplBase(){
    override fun fetchStatistics(request: FetchStatisticsRequest, responseObserver: StreamObserver<FetchStatisticsResponse>) {
        try {
            val result = statisticsService.fetchStatistics(request.userId, request.orgId, request.from, request.to)
            val statistics = result.statistics
            responseObserver.onNext(
                    FetchStatisticsResponse.newBuilder()
                            .setOrgId(statistics.orgId)
                            .setMaleCount(statistics.maleCount)
                            .setFemaleCount(statistics.femaleCount)
                            .setKidCount(statistics.kidCount)
                            .setAdultCount(statistics.adultCount)
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(exception.message ?: "Lỗi xảy ra trong quá trình lấy dữ liệu thống kê từ CSDL.")
                            .asException()
            )
        }
    }

    override fun pushStatistics(request: StatisticsRequest, responseObserver: StreamObserver<StatisticsResponse>) {
        try {
            statisticsService.pushStatistics(request.userId, request.orgId, request.maleCount, request.femaleCount, request.kidCount, request.adultCount)
            responseObserver.onNext(
                    StatisticsResponse.newBuilder()
                            .build()
            )
            responseObserver.onCompleted()
        }
        catch(exception: Exception){
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(exception.message ?: "Lỗi xảy ra trong quá trình ghi dữ liệu thống kê vào CSDL.")
                            .asException()
            )
        }
    }
}