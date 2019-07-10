package com.linty.engineeringidea.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import java.io.File

class MediaProviderUtil {
    companion object {
        fun getBitmapByUri(uri: String, context: Context): Bitmap {
            return MediaStore.Images.Media.getBitmap(context!!.contentResolver, Uri.fromFile(File(uri)))
        }
    }
}