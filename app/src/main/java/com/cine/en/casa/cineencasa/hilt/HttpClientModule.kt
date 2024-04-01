package com.cine.en.casa.cineencasa.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ViewModelComponent::class)
class HttpClientModule {

    @Provides
    fun retrofitBuilder(): Retrofit.Builder {

        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // Tiempo de espera para la conexión
                .readTimeout(10, TimeUnit.SECONDS).build())
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
    }


}