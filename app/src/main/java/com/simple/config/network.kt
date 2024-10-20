package com.simple.config

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.Objects

interface network {
    @GET("bootstrap-static/")
    @Headers("Accept: application/json")
    fun _getGeneralInfo(): Call<Objects>

    @GET("fixtures/")
    @Headers("Accept: application/json")
    fun Fixtures(): Call<ArrayList<Objects>>


    @GET("entry/{manager_id}/")
    @Headers("Accept: application/json")
    fun _getUserInfo(@Path("manager_id") id: Int): Call<Objects>


    @GET("leagues-classic/{manager_id}/standings/")
    @Headers("Accept: application/json")
    fun Manager_User_Basic_leags(@Path("manager_id") id: Int): Call<Objects>
}