package com.linty.engineeringidea.fragment.gallery

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.reflect.TypeToken
import com.linty.engineeringidea.NetworkService
import com.linty.engineeringidea.UploadImageSerializer
import com.linty.engineeringidea.model.ImageResponse
import com.linty.engineeringidea.model.UploadImage
import com.linty.engineeringidea.util.ConvertUtil
import com.linty.engineeringidea.util.MediaProviderUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class GalleryInterector : IInterector {
    @SuppressLint("CheckResult")
    override fun uploadImage(context: Context, uploadListener: IPresenter.IUploadImageListener, image: String) {
//        ConvertUtil.getBase64(MediaProviderUtil.getBitmapByUri(image, context))
        val img = UploadImage(
            //ERROR
            ConvertUtil.getBase64(MediaProviderUtil.getBitmapByUri(image, context))
            , "base64",
            "image.png",
            "image"
        )
        NetworkService.createRetrofit(createParser())
            .uploadImage(img)
            .enqueue(object : Callback<ImageResponse> {
                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    uploadListener.onErrorLoad(t.localizedMessage)
                }

                override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                    uploadListener.onSuccessLoad(response.body()!!)
                }
            })

    }

    private fun createParser(): GsonConverterFactory {
        return NetworkService.createParser(
            object : TypeToken<UploadImage>() {}.type,
            UploadImageSerializer()
        )

    }
}