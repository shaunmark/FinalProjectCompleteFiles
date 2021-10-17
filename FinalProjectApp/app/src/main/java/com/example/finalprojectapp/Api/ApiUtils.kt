package com.example.finalprojectapp.Api

class ApiUtils {
    companion object {
        private const val BASE_URL = "http://192.168.29.212/project/"
        fun getApiService(): ApiServices? {
            return ApiClient.getClient(BASE_URL)?.create(ApiServices::class.java)
        }
    }
}
