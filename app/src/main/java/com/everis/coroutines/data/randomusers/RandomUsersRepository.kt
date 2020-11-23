package com.everis.coroutines.data.randomusers

import com.everis.coroutines.domain.User

class RandomUsersRepository(
    private val remoteDataSource: RandomUsersRemoteDataSource,
    private val localDataSource: RandomUsersLocalDataSource,
) {
    suspend fun getUsers() = remoteDataSource.getUsers()
    suspend fun getFavoriteUsers() = localDataSource.getUsers()
    suspend fun saveFavoriteUser(user: User) = localDataSource.saveUser(user = user)
    suspend fun removeFavoriteUser(user: User) = localDataSource.removeUser(user = user)
}