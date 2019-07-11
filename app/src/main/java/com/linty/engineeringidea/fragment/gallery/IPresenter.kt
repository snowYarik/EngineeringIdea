package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

interface IPresenter {
    fun loadImage(context: Context, image: String)
    fun insertLink(context: Context, link: String)
    interface IUploadImageListener {
        fun onSuccessLoad(context: Context, imageResponse: ImageResponse)
        fun onErrorLoad(message: String)
    }


}