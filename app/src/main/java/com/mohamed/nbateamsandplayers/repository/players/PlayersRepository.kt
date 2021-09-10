package com.mohamed.nbateamsandplayers.repository.players

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players

interface PlayersRepository {
    /**
     * Retrieve all players from API
     */
    suspend fun getPlayers(perPage: Int?, page: Int?) : Players

    /**
     * Retrieve player details from API
     */
    suspend fun getPlayerDetail(id: Long): Player
}