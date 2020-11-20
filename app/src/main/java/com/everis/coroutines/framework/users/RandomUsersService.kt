package com.everis.coroutines.framework.users

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersService {
    @GET(".")
    suspend fun getUsers(@Query("results") results: Int = 50): Response<RandomUsersResponse>
}