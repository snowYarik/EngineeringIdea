package com.linty.engineeringidea.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

class GalleryPresenter(var interector: IInterector, var view: IView) : IPresenter,
    IPresenter.IUploadImageListener {
    override fun onSuccessLoad(imageResponse: ImageResponse) {
        view.successLoad(imageResponse)
    }

    override fun onErrorLoad(message: String) {
        view.errorLoad(message)
    }

    override fun loadImage(context: Context, image: String) {
        interector.uploadImage(context, this, image)
    }
}