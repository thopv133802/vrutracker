package com.thopham.projects.research.statisticsservice.services

import com.thopham.projects.research.statisticsservice.entities.StatisticGroupEntity
import com.thopham.projects.research.statisticsservice.entities.StatisticsEntity
import com.thopham.projects.research.statisticsservice.repositories.StatisticsRepository
import org.springframework.stereotype.Component

data class FetchStatisticsResult(
        val statistics: StatisticGroupEntity
)

@Component
class StatisticsService (private val statisticsRepository: StatisticsRepository, private val userService: UserService, private val organizationService: OrganizationService) {
    fun pushStatistics(userId: Int, orgId: Int, maleCount: Long, femaleCount: Long, kidCount: Long, adultCount: Long){
        val userDoesNotExists = !userService.existsById(userId)
        if(userDoesNotExists)
            throw Exception("Người dùng không tồn tại.")
        val organizationDoesNotBelongToUser = !organizationService.belongTo(userId, orgId)
        if (organizationDoesNotBelongToUser)
            throw Exception("Org không thuộc về người dùng.")
        statisticsRepository.save(
                StatisticsEntity.newInstance(
                        orgId,
                        maleCount,
                        femaleCount,
                        kidCount,
                        adultCount
                )
        )
    }

    fun fetchStatistics(userId: Int, orgId: Int, from: Long, to: Long): FetchStatisticsResult {
        val userDoesNotExists = !userService.existsById(userId)
        if(userDoesNotExists)
            throw Exception("Người dùng không tồn tại.")
        val organizationDoesNotBelongToUser = !organizationService.belongTo(userId, orgId)
        if (organizationDoesNotBelongToUser)
            throw Exception("Org không thuộc về người dùng.")
        val groups = statisticsRepository.fetchStatistics(orgId, from, to)
        if(groups.isEmpty())
            throw Exception("Chưa có số liệu thống kê nào cho org.")
        return FetchStatisticsResult(
                groups.first()
        )
    }
}