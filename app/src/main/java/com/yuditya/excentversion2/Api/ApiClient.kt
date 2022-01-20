package com.yuditya.excentversion2.Api

import com.google.gson.Gson
import com.yuditya.excentversion2.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getApiClient(base_url_local: String) : Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url_local)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
    }

}