package com.everis.coroutines.data.randomusers

import com.everis.coroutines.domain.User

interface RandomUsersDataSource {
    suspend fun getUsers(): List<User>
}