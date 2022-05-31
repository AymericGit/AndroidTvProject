package fr.supinternet.androidtv.data.network.api.trakt.model

import com.google.gson.annotations.SerializedName

/**
 * Example:
 *
 * {
 *     "title": "Frozen II",
 *     "year": 2019,
 *     "ids": {
 *     "trakt": 211394,
 *     "slug": "frozen-ii-2019",
 *     "imdb": "tt4520988",
 *     "tmdb": 330457
 * },
 *     "tagline": "The past is not what it seems.",
 *     "overview": "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
 *     "released": "2019-11-22",
 *     "runtime": 104,
 *     "country": "us",
 *     "trailer": "http: * youtube.com/watch?v=Zi4LMpSDccc",
 *     "homepage": "https: * movies.disney.com/frozen-2",
 *     "status": "released",
 *     "rating": 7.5881,
 *     "votes": 1277,
 *     "comment_count": 20,
 *     "updated_at": "2019-12-15T08:13:12.000Z",
 *     "language": "en",
 *     "available_translations": [
 *     "ar",
 *     "bg",
 *     "cn",
 *     "cs",
 *     "da",
 *     "de",
 *     "el",
 *     "en",
 *     "es",
 *     "fr",
 *     "he",
 *     "hu",
 *     "id",
 *     "it",
 *     "ja",
 *     "ka",
 *     "ko",
 *     "lt",
 *     "nl",
 *     "no",
 *     "pl",
 *     "pt",
 *     "ro",
 *     "ru",
 *     "sk",
 *     "sr",
 *     "sv",
 *     "tr",
 *     "tw",
 *     "uk",
 *     "vi",
 *     "zh"
 *     ],
 *     "genres": [
 *     "animation",
 *     "family",
 *     "music",
 *     "adventure"
 *     ],
 *     "certification": "PG"
 * }
 *
**/

data class MovieOverviewAPINetworkResponse(
    @SerializedName("available_translations")
    val availableTranslations: List<String>,
    @SerializedName("certification")
    val certification: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("ids")
    val ids: IdsAPINetworkResponse,
    @SerializedName("language")
    val language: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("released")
    val released: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trailer")
    val trailer: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("votes")
    val votes: Int,
    @SerializedName("year")
    val year: Int
)