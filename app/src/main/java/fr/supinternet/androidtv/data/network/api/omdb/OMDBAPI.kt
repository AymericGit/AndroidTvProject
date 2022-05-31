package fr.supinternet.androidtv.data.network.api.omdb

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.supinternet.androidtv.BuildConfig
import fr.supinternet.androidtv.data.network.api.omdb.model.MovieDetailsAPINetworkResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBAPI {

    @GET("/")
    fun movieAsync(@Query("i") imdbMovieId: String): Deferred<MovieDetailsAPINetworkResponse>

    companion object Factory {
        @Volatile
        private var retrofit: Retrofit? = null

        @Synchronized
        fun getInstance(): OMDBAPI {
            retrofit = retrofit
                ?: buildRetrofit()
            return retrofit!!.create(
                OMDBAPI::class.java)
        }

        private fun buildRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.OMDB_API_BASE_URL)
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
                    val request = chain.request()

                    return chain.proceed(
                        request
                            .newBuilder()
                            .url("${request.url}&apikey=${BuildConfig.OMDB_API_KEY}")
                            .build()
                    )
                }

            })
            .build()
    }

}