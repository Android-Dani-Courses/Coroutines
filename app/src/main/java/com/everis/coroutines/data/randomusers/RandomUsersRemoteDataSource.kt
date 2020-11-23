package com.everis.coroutines.data.randomusers

import com.everis.coroutines.domain.User

interface RandomUsersRemoteDataSource {
    suspend fun getUsers(): List<User>
}