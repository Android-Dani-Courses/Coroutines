package com.everis.coroutines.usecase.users

import com.everis.coroutines.data.randomusers.RandomUsersRepository
import com.everis.coroutines.domain.User

class RemoveFavoriteUser(private val repository: RandomUsersRepository) {
    suspend operator fun invoke(user: User) = repository.removeFavoriteUser(user = user)
}