package com.mohamed.nbateamsandplayers.models

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: Long,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: String?,
    @SerializedName("height_feet")
    val heightFeet: Double?,
    @SerializedName("height_inches")
    val heightInches: Double?,
    @SerializedName("weight_pounds")
    val weightPounds: Double?,
    @SerializedName("team")
    val team: Team?
)


data class Players(
    @SerializedName("data")
    val data: List<Player>
)