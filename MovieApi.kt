package com.example.workingwithjson.Jsonfiles

import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

        @GET("1.json")
         fun getMovies(): Call<MovieListResponse>

    @GET("2.json")
    fun getMoreMovies(): Call<MovieListResponse>

}