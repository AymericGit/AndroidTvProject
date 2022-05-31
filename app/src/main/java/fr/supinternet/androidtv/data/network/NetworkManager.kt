package fr.supinternet.androidtv.data.network

import fr.supinternet.androidtv.data.network.api.omdb.OMDBAPI
import fr.supinternet.androidtv.data.network.api.omdb.model.MovieDetailsAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.TraktAPI
import fr.supinternet.androidtv.data.network.model.Movie
import java.lang.Exception

object NetworkManager {

    private val traktAPI: TraktAPI = TraktAPI.getInstance()
    private val omdbAPI: OMDBAPI = OMDBAPI.getInstance()

    suspend fun getBoxOffice(includeRelatedMovies: Boolean = false): List<Movie> {
        val movies = mutableListOf<Movie>()

        val boxOffice = traktAPI.boxOfficeAsync().await()

        for (movie in boxOffice) {
            val movieId = movie.movie.ids.trakt
            val movieIMDBId = movie.movie.ids.imdb

            val movieDetails = traktAPI.movieAsync(movieId).await()
            val relatedMovies =
                if (includeRelatedMovies) traktAPI.relatedMoviesAsync(movieId).await() else null

            val omdb: MovieDetailsAPINetworkResponse? = try {
                omdbAPI.movieAsync(movieIMDBId).await()
            } catch (e: Exception) {
                null
            }

            movies.add(
                Movie(
                    boxOffice = movie,
                    movie = movieDetails,
                    related = relatedMovies,
                    omdb = omdb
                )
            )
        }

        return movies
    }

    suspend fun getAnticipatedMovies(includeRelatedMovies: Boolean = false): List<Movie> {
        val movies = mutableListOf<Movie>()

        val boxOffice = traktAPI.anticipatedAsync().await()

        for (movie in boxOffice) {
            val movieId = movie.movie.ids.trakt
            val movieIMDBId = movie.movie.ids.imdb

            val movieDetails = traktAPI.movieAsync(movieId).await()
            val relatedMovies =
                if (includeRelatedMovies) traktAPI.relatedMoviesAsync(movieId).await() else null

            val omdb: MovieDetailsAPINetworkResponse? = try {
                omdbAPI.movieAsync(movieIMDBId).await()
            } catch (e: Exception) {
                null
            }

            movies.add(
                Movie(
                    boxOffice = movie,
                    movie = movieDetails,
                    related = relatedMovies,
                    omdb = omdb
                )
            )
        }

        return movies
    }


}