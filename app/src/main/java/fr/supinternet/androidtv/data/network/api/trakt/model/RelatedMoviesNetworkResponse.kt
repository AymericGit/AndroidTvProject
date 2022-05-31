package fr.supinternet.androidtv.data.network.api.trakt.model

import com.google.gson.annotations.SerializedName

data class RelatedMovieAPINetworkResponse(
    @SerializedName("ids")
    val ids: IdsAPINetworkResponse,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)