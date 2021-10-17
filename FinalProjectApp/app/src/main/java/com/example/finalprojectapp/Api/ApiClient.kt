package com.example.finalprojectapp.Api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        var retrofit: Retrofit? = null
        val serviceTimeOut: Long = 30000
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getClient(baseUrl: String): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(
                        OkHttpClient.Builder()
                            .connectTimeout(serviceTimeOut, TimeUnit.MILLISECONDS)
                            .writeTimeout(serviceTimeOut, TimeUnit.MILLISECONDS)
                            .readTimeout(serviceTimeOut, TimeUnit.MILLISECONDS)
                            .build()
                    )
                    .build()
            }
            return retrofit
        }
    }
}