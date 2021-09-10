package com.mohamed.nbateamsandplayers.repository.teams

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.models.Team
import com.mohamed.nbateamsandplayers.models.Teams
import com.mohamed.nbateamsandplayers.network.api.NBAPlayersApi
import com.mohamed.nbateamsandplayers.network.api.NBATeamsApi
import org.koin.dsl.module
import java.lang.Exception

val teamsRepository = module {
    single<TeamsRepository> { TeamsRepositoryImpl(get()) }
}

class TeamsRepositoryImpl(private val teamsApi: NBATeamsApi): TeamsRepository {

    override suspend fun getTeams(page: Int?): Teams {
        return teamsApi.getNBATeams()
    }

    override suspend fun getTeam(id: Long): Team {
        return teamsApi.getSpecificTeam(id)
    }

}