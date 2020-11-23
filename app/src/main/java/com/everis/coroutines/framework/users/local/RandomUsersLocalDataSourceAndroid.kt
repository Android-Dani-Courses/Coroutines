package com.everis.coroutines.framework.users.local

import com.everis.coroutines.data.randomusers.RandomUsersLocalDataSource
import com.everis.coroutines.domain.User as DomainUser

class RandomUsersLocalDataSourceAndroid(
    private val database: AppDatabase
) : RandomUsersLocalDataSource {
    override suspend fun getUsers(): List<DomainUser> = database.userDao().getAll().map { user ->
        user.toDomain()
    }

    override suspend fun saveUser(user: DomainUser): List<DomainUser> {
        database.userDao().insert(user.toDatabase())
        return database.userDao().getAll().map { databaseUser ->
            databaseUser.toDomain()
        }
    }

    override suspend fun removeUser(user: DomainUser): List<DomainUser> {
        database.userDao().delete(user.toDatabase())
        return database.userDao().getAll().map { databaseUser ->
            databaseUser.toDomain()
        }
    }

    private fun User.toDomain() =
        DomainUser(id = id, name = name, address = address, image = image)

    private fun DomainUser.toDatabase() =
        User(id = id, name = name, address = address, image = image)
}