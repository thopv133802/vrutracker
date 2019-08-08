package com.thopham.projects.research.statisticsservice.repositories

import com.thopham.projects.research.statisticsservice.entities.StatisticGroupEntity
import com.thopham.projects.research.statisticsservice.entities.StatisticsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StatisticsRepository: JpaRepository<StatisticsEntity, Int> {
    @Query("select new com.thopham.projects.research.statisticsservice.entities.StatisticGroupEntity(statistics.orgId, sum(statistics.maleCount), sum(statistics.femaleCount), sum(statistics.kidCount), sum(statistics.adultCount)) from StatisticsEntity statistics where orgId = ?1 and created > ?2 and created < ?3 group by orgId")
    fun fetchStatistics(orgId: Int, from: Long, to: Long): List<StatisticGroupEntity>
}