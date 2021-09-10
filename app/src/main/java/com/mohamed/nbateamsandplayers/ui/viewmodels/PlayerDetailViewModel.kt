package com.mohamed.nbateamsandplayers.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.repository.players.PlayersRepository
import com.mohamed.nbateamsandplayers.ui.viewmodels.base.BaseAbstractViewModel
import com.mohamed.nbateamsandplayers.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.Exception

val playerDetailViewModelModule = module {
    viewModel { PlayerDetailViewModel(get()) }
}

class PlayerDetailViewModel(val playersRepository: PlayersRepository) : BaseAbstractViewModel() {

    private val _playerDetail: MutableLiveData<Resource<Player>> = MutableLiveData()
    val playerDetail: LiveData<Resource<Player>> = _playerDetail

    fun getPlayerDetail(idPlayer: Long){
        _playerDetail.postValue(Resource.loading())

        backgroundCoroutineScope.launch {
            supervisorScope {
                try {
                    val result = playersRepository.getPlayerDetail(idPlayer)
                    //Posting SUCCESS data to observers
                    _playerDetail.postValue(Resource.success(result))
                }catch (ex: Exception){
                    _playerDetail.postValue(Resource.error(ex))
                }
            }
        }
    }
}