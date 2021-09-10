package com.mohamed.nbateamsandplayers.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohamed.nbateamsandplayers.models.Teams
import com.mohamed.nbateamsandplayers.repository.teams.TeamsRepository
import com.mohamed.nbateamsandplayers.ui.viewmodels.base.BaseAbstractViewModel
import com.mohamed.nbateamsandplayers.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.Exception

val teamViewModule = module {
    viewModel {TeamsViewModel(get())}
}

class TeamsViewModel(val teamsRepository: TeamsRepository) : BaseAbstractViewModel() {

    private val _teams: MutableLiveData<Resource<Teams>> = MutableLiveData()
    val teams: LiveData<Resource<Teams>> = _teams

    fun getTeams(page: Int?){
        //Post LAODING status to the observers
        _teams.postValue(Resource.loading())

        backgroundCoroutineScope.launch {
            supervisorScope {
                try {
                    val result = teamsRepository.getTeams(page)
                    //Post SUCCESS Result to the observers
                    _teams.postValue(Resource.success(result))
                }catch (ex: Exception){
                    //Post ERROR Result to the observers
                    _teams.postValue(Resource.error(ex))
                }
            }
        }
    }

}