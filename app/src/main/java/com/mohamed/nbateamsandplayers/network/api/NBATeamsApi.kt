package com.mohamed.nbateamsandplayers.network.api

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.models.Team
import com.mohamed.nbateamsandplayers.models.Teams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NBATeamsApi {
    @GET("/teams?")
    suspend fun getNBATeams(@Query("page") page: Int? = null) : Teams

    @GET("/teams/{id}")
    suspend fun getSpecificTeam(@Path("id") idTeam: Long) : Team
}