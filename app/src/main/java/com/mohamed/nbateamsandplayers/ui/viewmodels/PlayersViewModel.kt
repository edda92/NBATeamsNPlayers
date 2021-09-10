package com.mohamed.nbateamsandplayers.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.repository.players.PlayersRepository
import com.mohamed.nbateamsandplayers.ui.viewmodels.base.BaseAbstractViewModel
import com.mohamed.nbateamsandplayers.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.Exception

val playersViewModelModule = module {
    viewModel { PlayersViewModel(get()) }
}

class PlayersViewModel(val playersRepository: PlayersRepository) : BaseAbstractViewModel() {

    private val _players: MutableLiveData<Resource<Players>> = MutableLiveData()
    val players: LiveData<Resource<Players>> = _players

    fun getPlayers(perPage: Int? = null, page: Int? = null){

        //Post LAODING status to the observers
        _players.postValue(Resource.loading())

        backgroundCoroutineScope.launch {
            supervisorScope {
                try {
                    val result = playersRepository.getPlayers(perPage, page)
                    //Post SUCCESS Result to the observers
                    _players.postValue(Resource.success(result))
                }catch (ex: Exception){
                    //Post ERROR Result to the observers
                    _players.postValue(Resource.error(ex))
                }
            }
       }
    }

}