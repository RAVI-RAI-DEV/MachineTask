package com.example.machinetask.network

import android.provider.ContactsContract.CommonDataKinds.Photo
import com.example.machinetask.model.Post
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int, @Query("limit") limit: Int): List<Post>
}
