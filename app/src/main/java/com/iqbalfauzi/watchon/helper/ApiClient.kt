package com.iqbalfauzi.watchon.helper

import com.iqbalfauzi.watchon.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Iqbal Fauzi on 18:42 02/11/19
 */
object ApiClient {

    private fun getInterceptor(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}