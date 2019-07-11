package com.linty.engineeringidea.network

import android.os.SystemClock
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * NetworkService is a class which has companion object for retrofit setting
 */
class NetworkService {

    companion object RetrofitBuilder {
        /**
         * The method for creating Retrofit object
         * @param gsonConverterFactory converter factory for json
         * @return retrofit object for ImguApi
         */
        fun createRetrofit(gsonConverterFactory: GsonConverterFactory): ImguAPI {
            val retrofit = Retrofit.Builder().baseUrl(ImguAPI.BASE_URL)
                .client(settingClient().build())
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(ImguAPI::class.java)
        }

        /**
         * The method for creating parser for json
         * @param type type of the class for getting or setting data from json
         * @param typeAdapter adapter for serialize or deserialize
         * @return converter factory for json
         */
        fun createParser(type: Type, typeAdapter: Any): GsonConverterFactory {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(type, typeAdapter)
            val gson = builder.create()
            return GsonConverterFactory.create(gson)
        }

        /**
         * The method for setting OkHttp client for retrofit
         * @return OkHttp builder
         */
        private fun settingClient(): OkHttpClient.Builder {
            val client = OkHttpClient.Builder()
            val dispatcher = Dispatcher()
            dispatcher.maxRequests = 1
            val interceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    SystemClock.sleep(1000)
                    return chain.proceed(chain.request())
                }
            }
            client.addNetworkInterceptor(interceptor)
            client.dispatcher(dispatcher)
            client.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Client-ID ${ImguAPI.CLIENT_ID}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            client.connectTimeout(10000, TimeUnit.MILLISECONDS)
            client.readTimeout(10000, TimeUnit.MILLISECONDS)
            return client
        }

    }

}