package com.sagar.nirfpredictor.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class User(
    @SerialName("name")
   var name:String,

    @SerialName("job")
    var job: String
)
