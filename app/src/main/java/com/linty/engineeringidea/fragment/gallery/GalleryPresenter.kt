package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

class GalleryPresenter(var interector: IInterector, var view: IView) : IPresenter,
    IPresenter.IUploadImageListener {
    override fun insertLink(context: Context, link: String) {
        interector.insertLink(context, link)
    }

    override fun onSuccessLoad(context: Context, imageResponse: ImageResponse) {
        view.successLoad(context, imageResponse)
    }

    override fun onErrorLoad(message: String) {
        view.errorLoad(message)
    }

    override fun loadImage(context: Context, image: String) {
        interector.uploadImage(context, this, image)
    }
}