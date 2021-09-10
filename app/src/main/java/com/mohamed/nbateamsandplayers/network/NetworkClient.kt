package com.mohamed.nbateamsandplayers.network

import com.mohamed.nbateamsandplayers.BuildConfig
import com.mohamed.nbateamsandplayers.network.api.NBAPlayersApi
import com.mohamed.nbateamsandplayers.network.api.NBATeamsApi
import com.mohamed.nbateamsandplayers.network.interceptor.AuthenticationInterceptor
import com.mohamed.nbateamsandplayers.network.interceptor.OKHttpLoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    factory { AuthenticationInterceptor() }
    factory { getOkHttpClient(get(), get())}
    factory { provideNBAPlayersApi(get()) }
    factory { OKHttpLoggingInterceptor() }
    factory { provideNBATeamsApi(get()) }
    single { getRetrofit(get()) }
}



/**
 * Returns Retrofit client for REST Api calls
 */
fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

/**
 * Return the Okhttp client for the network communication
 */
fun getOkHttpClient(authenticationInterceptor: AuthenticationInterceptor, httpLoggerInterceptor: OKHttpLoggingInterceptor): OkHttpClient{
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggerInterceptor)
        .addInterceptor(authenticationInterceptor)
        .build()
}

/**
 * Provides the Players API network
 */
fun provideNBAPlayersApi(retrofit: Retrofit): NBAPlayersApi {
    return retrofit.create(NBAPlayersApi::class.java)
}

/**
 * Provides the Teams API network
 */
fun provideNBATeamsApi(retrofit: Retrofit): NBATeamsApi {
    return retrofit.create(NBATeamsApi::class.java)
}
