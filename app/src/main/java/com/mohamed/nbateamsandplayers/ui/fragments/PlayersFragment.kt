package com.mohamed.nbateamsandplayers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.nbateamsandplayers.ui.viewmodels.PlayersViewModel
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.databinding.PlayersFragmentBinding
import com.mohamed.nbateamsandplayers.models.Players
import com.mohamed.nbateamsandplayers.ui.adapters.PlayersAdapter
import com.mohamed.nbateamsandplayers.ui.fragments.base.BaseAbstractFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val playerFragment = module {
    factory { PlayersFragment }
}

class PlayersFragment : BaseAbstractFragment() {

    private lateinit var playersBinding: PlayersFragmentBinding
    private var adapter: PlayersAdapter? = null
    private var idTeam = -1L

   val playersViewModel: PlayersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        playersBinding = PlayersFragmentBinding.inflate(inflater, container, false)
        return playersBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idTeam = arguments?.getLong(EXTRA_ID_TEAM, -1) ?: -1L
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setTitle(resources.getString(R.string.title_players))
    }

    override fun showEmptyList() {
        with(playersBinding){
            playersList.visibility = android.view.View.GONE
            emptyListTv.visibility = android.view.View.VISIBLE
        }
    }

    private fun initData(){
        playersViewModel.players.observe(viewLifecycleOwner){
            when{
                it.isLoading() -> {
                    showProgress()
                }
                it.isSuccess() -> {
                    hideProgress()
                    it.data?.let {
                        initAdatper(it)
                    } ?: showEmptyList()
                }
                it.isError() -> {
                    hideProgress()
                    showErrorDialog(null, null)
                }
            }
        }

        playersViewModel.getPlayers()
    }

    private fun initAdatper(players: Players){
        adapter = PlayersAdapter(players= players.data) { id ->
            onItemClicked(id)
        }
        val recyclerView = playersBinding.playersList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    private fun onItemClicked(idPlayer: Long){
        val bundle = Bundle().apply {
            putLong(PlayerDetailFragment.EXTRA_ID_PLAYER, idPlayer)
        }
        findNavController().navigate(R.id.action_playersFragment_to_playerDetailFragment, bundle)
    }

    companion object {
        fun newInstance() = PlayersFragment()
        const val TAG = "PlayersFragment"
        const val EXTRA_ID_TEAM = "EXTRA_ID_TEAM"
    }
}