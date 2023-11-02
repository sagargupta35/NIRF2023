package com.sagar.nirfpredictor.model

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sagar.nirfpredictor.network.FlaskApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer{
    val rankingDataRepository: RankingDataRepository
//    val userDataRepository: UserDataRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL = "http://127.0.0.1:5000/predict/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val flaskApiService: FlaskApiService by lazy {
        retrofit.create(FlaskApiService::class.java)
    }

    override val rankingDataRepository: RankingDataRepository by lazy {
        NetworkRankingRepository(flaskApiService)
    }

//    override val userDataRepository: UserDataRepository by lazy {
//        NetworkUserDataRepository(flaskApiService)
//    }

}