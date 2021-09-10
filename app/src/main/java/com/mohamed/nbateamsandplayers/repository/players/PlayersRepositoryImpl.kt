package com.mohamed.nbateamsandplayers.repository.players

import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.network.api.NBAPlayersApi
import org.koin.dsl.module

val playersRepository = module {
    single<PlayersRepository> { PlayersRepositoryImpl(get()) }
}

class PlayersRepositoryImpl(private val playersApi: NBAPlayersApi): PlayersRepository {

    override suspend fun getPlayers(perPage: Int?, page: Int?): Players {
        return playersApi.getNBAPlayers(perPage, page)
    }

    override suspend fun getPlayerDetail(id: Long): Player {
        return playersApi.getSpecificPlayer(id)
    }
}