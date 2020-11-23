package com.everis.coroutines.data.randomusers

import com.everis.coroutines.domain.User

interface RandomUsersLocalDataSource {
    suspend fun getUsers(): List<User>
    suspend fun saveUser(user: User): List<User>
    suspend fun removeUser(user: User): List<User>
}