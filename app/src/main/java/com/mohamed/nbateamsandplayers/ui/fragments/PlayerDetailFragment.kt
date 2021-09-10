package com.mohamed.nbateamsandplayers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.databinding.PlayerDetailFragmentBinding
import com.mohamed.nbateamsandplayers.models.Player
import com.mohamed.nbateamsandplayers.ui.fragments.base.BaseAbstractFragment
import com.mohamed.nbateamsandplayers.ui.viewmodels.PlayerDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerDetailFragment : BaseAbstractFragment() {

    private lateinit var playerViewBinding: PlayerDetailFragmentBinding
    val playerDetailViewModel: PlayerDetailViewModel by viewModel()

    private var idPlayer = -1L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        playerViewBinding = PlayerDetailFragmentBinding.inflate(inflater, container, false)
        return playerViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            idPlayer = it.getLong(EXTRA_ID_PLAYER, -1)
        } ?: kotlin.run {
            idPlayer = arguments?.getLong(EXTRA_ID_PLAYER, -1) ?: -1L
        }
        initData()
        setTitle(resources.getString(R.string.title_player_detail))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(EXTRA_ID_PLAYER, idPlayer)
        super.onSaveInstanceState(outState)
    }

    override fun showEmptyList() {
        //DO nothing there
    }

    private fun initData(){
        playerDetailViewModel.playerDetail.observe(viewLifecycleOwner){
            when{
                it.isLoading() -> {
                    showProgress()
                }
                it.isSuccess() -> {
                    hideProgress()
                    it.data?.let {
                        initView(player = it)
                    } ?: kotlin.run {
                        showErrorDialog(null, null)
                    }
                }
                it.isError() -> {
                    hideProgress()
                    showErrorDialog(null, null)
                }
            }
        }

        playerDetailViewModel.getPlayerDetail(idPlayer)
    }

    private fun initView(player: Player){
        with(playerViewBinding){
            playerFirstname.text = player.firstName
            playerLastname.text = player.lastName
            heightFeet.text = player.heightFeet?.toString() ?: "n.a."
            heightInches.text = player.heightInches?.toString() ?: "n.a."
            position.text = player.position ?: "n.a."
            teamName.text = player.team?.fullName ?: "n.a."
            weightPounds.text = player.weightPounds?.toString() ?: "n.a."
        }
    }

    companion object {
        const val EXTRA_ID_PLAYER = "EXTRA_ID_PLAYER"

        fun newInstance(idPlayer: Long) = PlayerDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_ID_PLAYER, idPlayer)
            }
        }
    }

}