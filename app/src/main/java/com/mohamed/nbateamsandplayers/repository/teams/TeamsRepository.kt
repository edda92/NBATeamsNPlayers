package com.mohamed.nbateamsandplayers.repository.teams

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.models.Team
import com.mohamed.nbateamsandplayers.models.Teams

interface TeamsRepository {
    /**
     * Retrieve all players from API
     */
    suspend fun getTeams(page: Int?) : Teams

    /**
     * Retrieve player details from API
     */
    suspend fun getTeam(id: Long): Team
}