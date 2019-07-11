package com.linty.engineeringidea.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

interface IView {
    fun successLoad( response: ImageResponse)
    fun errorLoad(message: String)

}