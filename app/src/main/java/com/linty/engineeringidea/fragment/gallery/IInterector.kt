package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.Link

interface IInterector {
    fun uploadImage(context: Context, uploadListener: IPresenter.IUploadImageListener, image: String)
    fun insertLink(
        context: Context, link: String
    )
}