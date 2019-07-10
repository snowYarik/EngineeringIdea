package com.linty.engineeringidea.fragment.gallery

import com.linty.engineeringidea.model.ImageResponse

interface IView {
    fun successLoad(response: ImageResponse)
    fun errorLoad(message: String)
}