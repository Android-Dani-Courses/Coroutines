package com.everis.coroutines.framework.users

data class RandomUsersResponse(
    val results: List<Result> = emptyList(),
)
data class Result(
    val name: Name = Name(),
    val location: Location = Location(),
    val login: Login,
    val picture: Picture = Picture(),
)
data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = "",
)
data class Location(
    val city: String = "",
    val state: String = "",
    val country: String = "",
)
data class Login(
    val uuid: String = "",
)
data class Picture(
    val large: String = "",
)