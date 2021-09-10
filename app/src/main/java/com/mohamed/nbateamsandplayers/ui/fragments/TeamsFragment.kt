package com.mohamed.nbateamsandplayers.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.databinding.TeamsFragmentBinding
import com.mohamed.nbateamsandplayers.models.Teams
import com.mohamed.nbateamsandplayers.ui.adapters.PlayersAdapter
import com.mohamed.nbateamsandplayers.ui.adapters.TeamsAdapter
import com.mohamed.nbateamsandplayers.ui.fragments.base.BaseAbstractFragment
import com.mohamed.nbateamsandplayers.ui.viewmodels.TeamsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module


val teamFragmentModule = module {
    factory { TeamsFragment }
}

class TeamsFragment : BaseAbstractFragment() {

    val teamsViewModel: TeamsViewModel by viewModel()

    private lateinit var teamsBinding: TeamsFragmentBinding
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teamsBinding = TeamsFragmentBinding.inflate(inflater, container, false)
        return teamsBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setTitle(resources.getString(R.string.title_teams))
    }

    override fun showEmptyList() {
        with(teamsBinding){
            teamsList.visibility = View.GONE
            emptyListTv.visibility = View.VISIBLE
        }
    }

    private fun initData(){
        teamsViewModel.teams.observe(viewLifecycleOwner){
            when{
                it.isLoading() -> {
                    showProgress()
                }
                it.isError() -> {
                    hideProgress()
                    showErrorDialog(null, null)
                }
                it.isSuccess() -> {
                    hideProgress()
                    it.data?.let {
                        initList(it)
                    } ?: showEmptyList()
                }
            }
        }

        teamsViewModel.getTeams(null)
    }

    private fun initList(teams: Teams) {
        adapter = TeamsAdapter(teams= teams.data) { id ->
            onTeamClicked(id)
        }
        val recyclerView = teamsBinding.teamsList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun onTeamClicked(idTeam: Long){
        val bundle = Bundle().apply {
            putLong(PlayersFragment.EXTRA_ID_TEAM, idTeam)
        }
        findNavController().navigate(R.id.action_teamsFragment_to_playersFragment, bundle)
    }

    companion object {
        fun newInstance() = TeamsFragment()
    }
}