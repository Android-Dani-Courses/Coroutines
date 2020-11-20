package com.everis.coroutines.usecase.users

import com.everis.coroutines.data.randomusers.RandomUsersRepository

class GetUsers(private val repository: RandomUsersRepository) {
    suspend operator fun invoke() = repository.getUsers()
}