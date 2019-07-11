package com.linty.engineeringidea.gallery

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.reflect.TypeToken
import com.linty.engineeringidea.db.AppDatabase
import com.linty.engineeringidea.model.Link
import com.linty.engineeringidea.network.NetworkService
import com.linty.engineeringidea.network.UploadImageSerializer
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
                    val link = response.body()!!.data.link
                    insertLink(context, link)
                    uploadListener.onSuccessLoad(response.body()!!)
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun insertLink(context: Context, link: String) {
        Single.fromCallable {
            AppDatabase.getDB(context).linkDao().insertLink(Link(null, link))
        }
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, {
                if (it != null)
                    throw Exception(it.localizedMessage)
            })
    }

    private fun createParser(): GsonConverterFactory {
        return NetworkService.createParser(
            object : TypeToken<UploadImage>() {}.type,
            UploadImageSerializer()
        )

    }
}