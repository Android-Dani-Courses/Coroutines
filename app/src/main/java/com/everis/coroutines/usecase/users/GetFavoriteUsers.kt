package com.everis.coroutines.usecase.users

import com.everis.coroutines.data.randomusers.RandomUsersRepository

class GetFavoriteUsers(private val repository: RandomUsersRepository) {
    suspend operator fun invoke() = repository.getFavoriteUsers()
}