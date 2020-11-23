package com.everis.coroutines.framework.users.di

import android.content.Context
import androidx.room.Room
import com.everis.coroutines.data.randomusers.RandomUsersLocalDataSource
import com.everis.coroutines.data.randomusers.RandomUsersRemoteDataSource
import com.everis.coroutines.framework.users.local.AppDatabase
import com.everis.coroutines.framework.users.local.RandomUsersLocalDataSourceAndroid
import com.everis.coroutines.framework.users.remote.RandomUsersRemoteDataSourceAndroid
import com.everis.coroutines.framework.users.remote.RandomUsersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUsersProvider private constructor(private val context: Context) {
    private val provideService: RandomUsersService
        get() = Retrofit.Builder()
            .baseUrl("https://api.randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUsersService::class.java)
    val provideRemoteDataSource: RandomUsersRemoteDataSource
        get() = RandomUsersRemoteDataSourceAndroid(service = provideService)

    private val provideDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "coroutines-app-database"
        ).build()
    }
    val provideLocalDataSource: RandomUsersLocalDataSource
        get() = RandomUsersLocalDataSourceAndroid(database = provideDatabase)


    companion object {
        private lateinit var instance: RandomUsersProvider
        fun getInstance(context: Context) = if (!::instance.isInitialized) {
            RandomUsersProvider(context = context.applicationContext).apply {
                instance = this
            }
        } else {
            instance
        }
    }
}