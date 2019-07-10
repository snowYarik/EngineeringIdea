package com.linty.engineeringidea.fragment.gallery

import android.content.Context
import com.linty.engineeringidea.model.ImageResponse

class GalleryPresenter(var interector: IInterector, var view: IView) : IPresenter, IPresenter.IUploadImageListener {
    override fun <T> onSuccessLoad(type: T) {
        view.successLoad(type as ImageResponse)
    }


    override fun onErrorLoad(message: String) {
        view.errorLoad(message)
    }

//    override fun onSuccessLoad(response: ImageResponse) {
//        view.successLoad(response)
//    }
//
//    override fun onErrorLoad(message: String) {
//        view.errorLoad(message)
//    }

    override fun loadImage(context: Context, image: String) {
        interector.uploadImage(context, this, image)
    }
}