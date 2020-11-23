package com.everis.coroutines.framework.users.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val address: String = "",
    val image: String = "",
)