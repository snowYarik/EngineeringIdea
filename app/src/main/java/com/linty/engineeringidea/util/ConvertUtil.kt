package com.linty.engineeringidea.util

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * ConverterUtil is a class for converting data
 */
class ConvertUtil {
    companion object {
        /**
         * getBase64 is a method for getting base64 from bitmap
         * @param bitmap bitmap for base64
         * @return base64 string
         */
        fun getBase64(bitmap: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)

        }
    }
}