package com.everis.coroutines.usecase.users

import com.everis.coroutines.data.randomusers.RandomUsersRepository
import com.everis.coroutines.domain.User

class SaveFavoriteUser(private val repository: RandomUsersRepository) {
    suspend operator fun invoke(user: User) = repository.saveFavoriteUser(user = user)
}