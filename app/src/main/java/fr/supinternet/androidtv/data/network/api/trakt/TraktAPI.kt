package fr.supinternet.androidtv.data.network.api.trakt

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.supinternet.androidtv.BuildConfig
import fr.supinternet.androidtv.data.network.api.trakt.model.MovieAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.model.MovieOverviewAPINetworkResponse
import fr.supinternet.androidtv.data.network.api.trakt.model.RelatedMovieAPINetworkResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TraktAPI {

    @GET("movies/boxoffice")
    fun boxOfficeAsync(): Deferred<List<MovieAPINetworkResponse>>

    @GET("movies/anticipated")
    fun anticipatedAsync(): Deferred<List<MovieAPINetworkResponse>>

    @GET("movies/{id}?extended=full")
    fun movieAsync(@Path("id") movieId: Int): Deferred<MovieOverviewAPINetworkResponse>

    @GET("movies/{id}/related")
    fun relatedMoviesAsync(@Path("id") movieId: Int): Deferred<List<RelatedMovieAPINetworkResponse>>

    companion object Factory {
        @Volatile
        private var retrofit: Retrofit? = null

        @Synchronized
        fun getInstance(): TraktAPI {
            retrofit = retrofit
                ?: buildRetrofit()
            return retrofit!!.create(
                TraktAPI::class.java)
        }

        private fun buildRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.TRAKT_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client())
            .build()

        private fun client() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    // Inject the header in all requests
                    return chain.proceed(
                        chain.request()
                            .newBuilder()
                            .addHeader("trakt-api-key", BuildConfig.TRAKT_API_KEY)
                            .build()
                    )
                }

            })
            .build()
    }

}