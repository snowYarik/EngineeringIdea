package com.linty.engineeringidea.network

import com.linty.engineeringidea.model.ImageResponse
import com.linty.engineeringidea.model.UploadImage
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * The interface API for http requests
 */
interface ImguAPI {
    companion object {
        const val BASE_URL: String = "https://api.imgur.com/3/"
        const val CLIENT_ID: String = "ec8fff0be67a98f"
    }

    /**
     * The method for posting image
     * @param image data for upload image
     */
    @POST(value = "upload")
    fun uploadImage(@Body image: UploadImage): Call<ImageResponse>
}