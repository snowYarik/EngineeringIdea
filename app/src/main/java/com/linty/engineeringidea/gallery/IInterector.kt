package com.linty.engineeringidea.gallery

import android.content.Context


interface IInterector {
    fun uploadImage(context: Context, uploadListener: IPresenter.IUploadImageListener, image: String)
    fun insertLink(
        context: Context, link: String
    )
}