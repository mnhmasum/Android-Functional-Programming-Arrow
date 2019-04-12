package com.artifactslab.newspan.retrofit
import com.artifactslab.newspan.dto.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("v2/top-headlines")
    fun getRepositories(@Query("country") language: String,
                                 @Query("apiKey") order: String): Call<News>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)
