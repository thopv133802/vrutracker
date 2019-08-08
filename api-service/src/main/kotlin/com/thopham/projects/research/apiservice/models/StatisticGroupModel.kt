package com.thopham.projects.research.apiservice.models

data class StatisticGroupModel(
        val orgId: Int,
        val maleCount: Long,
        val femaleCount: Long,
        val kidCount: Long,
        val adultCount: Long
){
    constructor(): this(0, 0, 0, 0, 0)
}