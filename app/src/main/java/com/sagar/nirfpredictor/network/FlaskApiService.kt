package com.sagar.nirfpredictor.network

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FlaskApiService {

//    @FormUrlEncoded
//    @POST("your_endpoint_here")
//    suspend fun sendData(
//        @Field("ss") ss: String,
//        @Field("fsr") fsr: String,
//        @Field("fqe") fqe: String,
//        @Field("fru") fru: String,
//        @Field("pu") pu: String,
//        @Field("qp") qp: String,
//        @Field("ipr") ipr: String,
//        @Field("fppp") fppp: String,
//        @Field("gue") gue: String,
//        @Field("gphd") gphd: String,
//        @Field("rd") rd: String,
//        @Field("wd") wd: String,
//        @Field("escs") escs: String,
//        @Field("pcs") pcs: String,
//        @Field("pr") pr: String,
//    ): Call<RankingData>

    @GET("predict")
    suspend fun getRank(): RankingData




}