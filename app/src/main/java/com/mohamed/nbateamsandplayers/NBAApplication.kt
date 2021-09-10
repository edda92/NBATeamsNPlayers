package com.mohamed.nbateamsandplayers

import android.app.Application
import com.mohamed.nbateamsandplayers.network.apiModule
import com.mohamed.nbateamsandplayers.repository.players.playersRepository
import com.mohamed.nbateamsandplayers.repository.teams.teamsRepository
import com.mohamed.nbateamsandplayers.ui.fragments.playerFragment
import com.mohamed.nbateamsandplayers.ui.fragments.teamFragmentModule
import com.mohamed.nbateamsandplayers.ui.viewmodels.playerDetailViewModelModule
import com.mohamed.nbateamsandplayers.ui.viewmodels.playersViewModelModule
import com.mohamed.nbateamsandplayers.ui.viewmodels.teamViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * NBAApplication
 */
class NBAApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        //Start DI with Koin
        startKoin {
            androidLogger()
            androidContext(this@NBAApplication)
            modules(listOf(
                apiModule,
                playerDetailViewModelModule,
                playersViewModelModule,
                playersRepository,
                playerFragment,
                teamsRepository,
                teamViewModule,
                teamFragmentModule
            ))
        }
    }
}