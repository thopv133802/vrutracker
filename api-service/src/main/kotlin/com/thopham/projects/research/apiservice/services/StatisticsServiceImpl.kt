package com.thopham.projects.research.apiservice.services

import com.proto.statistics.FetchStatisticsRequest
import com.proto.statistics.StatisticsRequest
import com.proto.statistics.StatisticsServiceGrpc
import com.thopham.projects.research.apiservice.core.ChannelUtils
import com.thopham.projects.research.apiservice.models.StatisticGroupModel
import org.springframework.stereotype.Component

data class FetchStatisticsResult(
        val statistics: StatisticGroupModel
)

class PushStatisticsResult

@Component
class StatisticsServiceImpl(private val tokenGenerator: TokenGenerator) {
    fun fetchStatistics(token: String, orgId: Int, from: Long, to: Long): FetchStatisticsResult {
        val user = tokenGenerator.resolve(token)
        val channel = ChannelUtils.buildChannel("statistics-service", 18081)
        val stub = StatisticsServiceGrpc.newBlockingStub(channel)
        val response = stub.fetchStatistics(
                FetchStatisticsRequest.newBuilder()
                        .setUserId(user.id)
                        .setOrgId(orgId)
                        .setFrom(from)
                        .setTo(to)
                        .build()
        )
        channel.shutdown()
        return FetchStatisticsResult(
                StatisticGroupModel(
                        response.orgId,
                        response.maleCount,
                        response.femaleCount,
                        response.kidCount,
                        response.adultCount
                )
        )
    }

    fun pushStatistics(token: String, orgId: Int, maleCount: Long, femaleCount: Long, kidCount: Long, adultCount: Long): PushStatisticsResult {
        val user = tokenGenerator.resolve(token)
        val channel = ChannelUtils.buildChannel("statistics-service", 18081)
        val stub = StatisticsServiceGrpc.newBlockingStub(channel)
        val response = stub.pushStatistics(
                StatisticsRequest.newBuilder()
                        .setUserId(user.id)
                        .setOrgId(orgId)
                        .setMaleCount(maleCount)
                        .setFemaleCount(femaleCount)
                        .setKidCount(kidCount)
                        .setAdultCount(adultCount)
                        .build()
        )
        channel.shutdown()
        return PushStatisticsResult()
    }

}