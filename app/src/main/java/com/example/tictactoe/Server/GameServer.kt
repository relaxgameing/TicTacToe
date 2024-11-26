package com.example.tictactoe.Server


import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL ="https://relaxgamein-tictactoeba-92.deno.dev/"
//private const val BASE_URL ="https://relaxgamein-tictactoeba-92-ce17dhgb3yr3.deno.dev/"
//private const val BASE_URL ="http://10.0.2.2:3000/"

val retro = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

val retroService = retro.create(ApiInterface::class.java)

interface ApiInterface {
    @Headers(
        "Accept: application/json"
    )

    @GET("game/test")
    suspend fun gameTest(): TestResponse

    @GET("game/room/username")
    suspend fun checkUsername(
        @Query("name") name: String
    ): PayloadResponse

    @GET("game/room/new")
    suspend fun createRoom(
        @Query("username") username: String,
        @Query("mode") mode: String
    ): Response<NewRoomResponse>


}