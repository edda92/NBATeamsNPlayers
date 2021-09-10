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
import com.mohamed.nbateamsandplayers.models.Team


class TeamsAdapter(
    val teams: List<Team>,
    val onClickListener: (Long) -> Unit
): RecyclerView.Adapter<TeamsAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = teams.get(position)
        holder.teamName.text = item.fullName
        holder.cardContainer.setOnClickListener {
            onClickListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val teamName: TextView = itemView.findViewById(R.id.teamName)
        val cardContainer: MaterialCardView = itemView.findViewById(R.id.teamCard)
    }
}