package com.mohamed.nbateamsandplayers.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mohamed.nbateamsandplayers.R
import com.mohamed.nbateamsandplayers.models.Player


class PlayersAdapter(
    val players: List<Player>,
    val onClickListener: (Long) -> Unit
): RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = players.get(position)
        holder.playerName.text = item.firstName
        holder.playerLastname.text = item.lastName
        holder.cardContainer.setOnClickListener {
            onClickListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return players.size
    }

    class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.playerFirstname)
        val playerLastname: TextView = itemView.findViewById(R.id.playerLastname)
        val cardContainer: MaterialCardView = itemView.findViewById(R.id.playerCard)
    }
}