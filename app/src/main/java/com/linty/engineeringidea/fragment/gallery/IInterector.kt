package com.linty.engineeringidea.fragment.gallery

import android.content.Context

interface IInterector {
    fun uploadImage(context: Context, uploadListener: IPresenter.IUploadImageListener, image: String)
}