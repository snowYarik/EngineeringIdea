package com.linty.engineeringidea

import android.os.SystemClock
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.linty.engineeringidea.model.UploadImage
import com.linty.engineeringidea.network.ImguAPI
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

class NetworkService {

    companion object RetrofitBuilder {

        fun createRetrofit(gsonConverterFactory: GsonConverterFactory): ImguAPI {
            val retrofit = Retrofit.Builder().baseUrl(ImguAPI.BASE_URL)
                .client(settingClient().build())
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(ImguAPI::class.java)
        }

        fun createParser(type: Type, typeAdapter: Any): GsonConverterFactory {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(type, typeAdapter)
            val gson = builder.create()
            return GsonConverterFactory.create(gson)
        }

        fun settingClient(): OkHttpClient.Builder {
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