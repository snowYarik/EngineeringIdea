package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

interface IView {
    fun successLoad(context: Context, response: ImageResponse)
    fun errorLoad(message: String)

}