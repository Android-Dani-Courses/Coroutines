package com.everis.coroutines.data.randomusers

class RandomUsersRepository(private val dataSource: RandomUsersDataSource) {
    suspend fun getUsers() = dataSource.getUsers()
}