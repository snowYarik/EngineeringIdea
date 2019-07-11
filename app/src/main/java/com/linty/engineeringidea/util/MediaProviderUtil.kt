package com.linty.engineeringidea.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import java.io.File

/**
 * MediaProviderUtil is a class for getting data from Phone storage
 */
class MediaProviderUtil {
    companion object {
        /**
         * getBitmapByUri is a method for getting bitmap by uri
         * @param uri the image's uri
         * @param context the context
         * @return bitmap image
         */
        fun getBitmapByUri(uri: String, context: Context): Bitmap {
            return MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.fromFile(File(uri)))
        }
    }
}