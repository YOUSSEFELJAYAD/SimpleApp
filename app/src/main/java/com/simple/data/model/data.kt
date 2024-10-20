package com.simple.data.model

data class User(val token: String, val name: String, val address: String)


var userByPhone  = User(
    token = "8bb2177f2274803a9b2066e914aab6",
    name = "Phone",
    address = "ADDRESS"
)
var userByEmail  = User(

    token = "5510edec7cccb2d7710009882bb9c4c",
    name = "Email",
    address = "ADDRESS"
)