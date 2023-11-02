package com.sagar.nirfpredictor.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RankingData(
    @SerialName("rank")
    val rank: String,
)
