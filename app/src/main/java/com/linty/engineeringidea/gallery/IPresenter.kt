package com.linty.engineeringidea.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

interface IPresenter {
    fun loadImage(context: Context, image: String)

    interface IUploadImageListener {
        fun onSuccessLoad(imageResponse: ImageResponse)
        fun onErrorLoad(message: String)
    }


}