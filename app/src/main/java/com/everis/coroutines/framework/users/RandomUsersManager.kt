package com.everis.coroutines.framework.users

import com.everis.coroutines.data.randomusers.RandomUsersDataSource
import com.everis.coroutines.domain.User
import java.lang.Exception

class RandomUsersManager(private val service: RandomUsersService) : RandomUsersDataSource {
    override suspend fun getUsers(): List<User> = try {
        val response = service.getUsers()
        if (response.isSuccessful) {
            response.body()?.results?.map { result ->
                User(
                    id = result.login.uuid,
                    name = "${result.name.title} ${result.name.first} ${result.name.last}",
                    address = "${result.location.city} ${result.location.state} ${result.location.country}",
                    image = result.picture.large,
                )
            } ?: emptyList()
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        emptyList()
    }
}