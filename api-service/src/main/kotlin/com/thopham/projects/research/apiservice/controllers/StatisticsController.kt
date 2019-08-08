package com.thopham.projects.research.apiservice.controllers

import com.thopham.projects.research.apiservice.core.Response
import com.thopham.projects.research.apiservice.services.FetchStatisticsResult
import com.thopham.projects.research.apiservice.services.PushStatisticsResult
import com.thopham.projects.research.apiservice.services.StatisticsServiceImpl
import org.springframework.web.bind.annotation.*

data class PushStatisticsRequest(
        val orgId: Int,
        val maleCount: Long,
        val femaleCount: Long,
        val kidCount: Long,
        val adultCount: Long
)

@RestController
@RequestMapping("/statistics")
class StatisticsController(private val statisticsService: StatisticsServiceImpl) {
    @GetMapping("")
    fun fetchStatistics(@RequestAttribute("token") token: String, @RequestParam("orgId") orgId: Int, @RequestParam("from") from: Long, @RequestParam("to") to: Long): Response<FetchStatisticsResult>{
        return try{
            val payload = statisticsService.fetchStatistics(token, orgId, from, to)
            Response(
                    true,
                    "Tải thành công số liệu thống kê",
                    payload
            )
        }
        catch (exception: Exception){
            Response(
                    false,
                    exception.message ?: "Tải số liệu thống kê thất bại.",
                    null
            )
        }
    }
    @PostMapping("")
    fun pushStatistics(@RequestAttribute("token") token: String, @RequestBody request: PushStatisticsRequest): Response<PushStatisticsResult> {
        return try{
            val payload = statisticsService.pushStatistics(token, request.orgId, request.maleCount, request.femaleCount, request.kidCount, request.adultCount)
            Response(
                    true,
                    "Đẩy số liệu thống kê thành công.",
                    payload
            )
        }
        catch (exception: Exception){
            Response(
                    false,
                    exception.message ?: "Đẩy số liệu thống kê thất bại.",
                    null
            )
        }
    }
}