package com.mohamed.nbateamsandplayers.network.api

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NBAPlayersApi {
    @GET("/players?")
    suspend fun getNBAPlayers(@Query("per_page") perPage: Int? = null, @Query("page") page: Int? = null) : Players

    @GET("/players/{id}")
    suspend fun getSpecificPlayer(@Path("id") idPlayer: Long) : Player
}