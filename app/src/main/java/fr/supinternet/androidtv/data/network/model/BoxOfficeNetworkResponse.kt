package fr.supinternet.androidtv.data.network.model

import android.os.Parcel
import android.os.Parcelable
import fr.supinternet.androidtv.data.network.api.omdb.model.MovieDetailsAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.model.MovieAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.model.MovieOverviewAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.model.RelatedMovieAPINetworkResponse
import java.text.SimpleDateFormat
import java.util.*

data class Movie(
    val id: Int,
    val name: String?,
    val year: Int,
    val tagline: String?,
    val overview: String?,
    val releaseDate: Long?,
    val actors: List<String>?,
    val directors: List<String>?,
    val writers: List<String>?,
    val awards: String?,
    val runtime: Int?,
    val posterURL: String?,
    val trailerURL: String?,
    val rating: Double?,
    val revenue: Int,
    val relatedMovies: List<RelatedBoxOfficeMovie>?
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readInt(),
        parcel.createTypedArrayList(RelatedBoxOfficeMovie)
    ) {
    }

    constructor(
        boxOffice: MovieAPINetworkResponse,
        movie: MovieOverviewAPINetworkResponse?,
        related: List<RelatedMovieAPINetworkResponse>?,
        omdb: MovieDetailsAPINetworkResponse?
    ) :
            this(
                id = boxOffice.movie.ids.trakt,
                name = boxOffice.movie.title,
                year = boxOffice.movie.year,
                tagline = movie?.tagline ?: omdb?.plot,
                overview = movie?.overview ?: omdb?.plot,
                releaseDate = movie?.released?.let {
                    if (it != "N/A") {
                        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(it)?.time
                    } else {
                        null
                    }
                } ?: omdb?.released?.let {
                    if (it != "N/A") {
                        SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(it)?.time
                    } else {
                        null
                    }
                },
                actors = omdb?.actors?.split(Regex(",( )?")),
                directors = omdb?.director?.split(Regex(",( )?")),
                writers = omdb?.writer?.split(Regex(",( )?")),
                awards = if (omdb?.awards == "N/A") null else omdb?.awards,
                runtime = movie?.runtime
                    ?: if (omdb?.runtime == "N/A") null else omdb?.runtime?.replace(
                        Regex(" .*"),
                        ""
                    )?.toInt(),
                posterURL = omdb?.poster,
                trailerURL = movie?.trailer,
                rating = movie?.rating
                    ?: if (omdb?.imdbRating == "N/A") null else omdb?.imdbRating?.toDouble(),
                revenue = boxOffice.revenue,
                relatedMovies = related?.map {
                    RelatedBoxOfficeMovie(it)
                }
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(year)
        parcel.writeString(tagline)
        parcel.writeString(overview)
        parcel.writeValue(releaseDate)
        parcel.writeStringList(actors)
        parcel.writeStringList(directors)
        parcel.writeStringList(writers)
        parcel.writeString(awards)
        parcel.writeValue(runtime)
        parcel.writeString(posterURL)
        parcel.writeString(trailerURL)
        parcel.writeValue(rating)
        parcel.writeInt(revenue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}

data class RelatedBoxOfficeMovie(
    val id: Int,
    val name: String?,
    val year: Int
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    constructor(resp: RelatedMovieAPINetworkResponse) : this(
        id = resp.ids.trakt,
        name = resp.title,
        year = resp.year
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RelatedBoxOfficeMovie> {
        override fun createFromParcel(parcel: Parcel): RelatedBoxOfficeMovie {
            return RelatedBoxOfficeMovie(parcel)
        }

        override fun newArray(size: Int): Array<RelatedBoxOfficeMovie?> {
            return arrayOfNulls(size)
        }
    }

}