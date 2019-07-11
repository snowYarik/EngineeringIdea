package com.linty.engineeringidea.fragment.gallery

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.reflect.TypeToken
import com.linty.engineeringidea.AppDatabase
import com.linty.engineeringidea.Link
import com.linty.engineeringidea.NetworkService
import com.linty.engineeringidea.UploadImageSerializer
import com.linty.engineeringidea.model.ImageResponse
import com.linty.engineeringidea.model.UploadImage
import com.linty.engineeringidea.util.ConvertUtil
import com.linty.engineeringidea.util.MediaProviderUtil
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Callable

class GalleryInterector : IInterector {

    @SuppressLint("CheckResult")
    override fun uploadImage(context: Context, uploadListener: IPresenter.IUploadImageListener, image: String) {
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
                    val link = response.body()!!.data!!.link
                    insertLink(context, link)
                    uploadListener.onSuccessLoad(context, response.body()!!)
                }
            })
//        uploadListener.onSuccessLoad(context, ImageResponse(null, null, null))

    }

    @SuppressLint("CheckResult")
    override fun insertLink(context: Context, link: String) {
        Single.fromCallable {
            AppDatabase.getDB(context).linkDao().insertLink(Link(null, link))
        }
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    private fun createParser(): GsonConverterFactory {
        return NetworkService.createParser(
            object : TypeToken<UploadImage>() {}.type,
            UploadImageSerializer()
        )

    }
}