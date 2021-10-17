package com.example.finalprojectapp.Api

import com.example.finalprojectapp.DataClasses.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @POST("login.php")
    fun sendUserLogin(
        @Body user: User
    ): Call<LoginSuccess>

    @POST("register.php")
    fun sendVehicleData(
        @Body registerVehicle: RegisterVehicle
    ): Call<LoginSuccess>

    @GET("logbook.php")
    fun getLogBook(
    ): Call<List<LogBookData>>

    @POST("search.php")
    fun searchVehicle(
        @Body searchData: SearchData
    ): Call<List<SearchResult>>

    @POST("delete.php")
    fun deleteVehicle(
        @Body deleteVehicle: DeleteVehicle
    ): Call<DeleteSuccess>
}