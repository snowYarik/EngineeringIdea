package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.ILoadListener
import com.linty.engineeringidea.model.ImageResponse

interface IPresenter {
    fun loadImage(context: Context, image: String)
    interface IUploadImageListener : ILoadListener
}