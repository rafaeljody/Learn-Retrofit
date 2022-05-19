package com.example.myapplication.retrofit

import com.example.myapplication.MainModel
import retrofit2.Call
import retrofit2.http.GET
import java.util.jar.Manifest

interface ApiEndpoint {
    @GET("?results=200")
    fun getData(): Call<MainModel>
}