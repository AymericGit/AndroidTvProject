package fr.supinternet.androidtv.data.network.api.trakt.model

import com.google.gson.annotations.SerializedName

/**
 * Example:
 *
 * {
 *    "revenue": 34700000,
 *    "movie": {
 *      "title": "Frozen II",
 *      "year": 2019,
 *    "ids": {
 *      "trakt": 211394,
 *      "slug": "frozen-ii-2019",
 *      "imdb": "tt4520988",
 *      "tmdb": 330457
 *    }
 *  }
 */
data class MovieAPINetworkResponse(
    @SerializedName("movie")
    val movie: BoxOfficeAPINetworkResponseMovie,
    @SerializedName("revenue")
    val revenue: Int
)

data class BoxOfficeAPINetworkResponseMovie(
    @SerializedName("ids")
    val ids: IdsAPINetworkResponse,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)