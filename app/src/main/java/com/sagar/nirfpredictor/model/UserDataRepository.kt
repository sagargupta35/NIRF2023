package com.sagar.nirfpredictor.model
//
//import com.sagar.nirfpredictor.network.FlaskApiService
//import com.sagar.nirfpredictor.network.User
//import retrofit2.Call
//import retrofit2.http.Body
//
//interface UserDataRepository {
//    suspend fun sendData(@Body user: User): User
//}
//
//class NetworkUserDataRepository(
//    private val flaskApiService: FlaskApiService
//): UserDataRepository{
//    override suspend fun sendData(user: User): User {
//        return flaskApiService.sendData(user)
//    }
//
//}