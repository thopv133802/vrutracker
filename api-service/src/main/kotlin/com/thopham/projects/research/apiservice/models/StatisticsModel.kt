package com.thopham.projects.research.apiservice.models


data class StatisticsModel(
        val id: Int,
        val orgId: Int,
        val maleCount: Long,
        val femaleCount: Long,
        val kidCount: Long,
        val adultCount: Long,
        val created: Long
){
    constructor(): this(0, 0, 0, 0, 0, 0, 0)
    companion object{
        fun newInstance(orgId: Int, maleCount: Long, femaleCount: Long, kidCount: Long, adultCount: Long): StatisticsModel {
            return StatisticsModel(
                    id = 0,
                    orgId = orgId,
                    maleCount = maleCount,
                    femaleCount = femaleCount,
                    kidCount = kidCount,
                    adultCount = adultCount,
                    created = System.currentTimeMillis()
            )
        }
    }
}