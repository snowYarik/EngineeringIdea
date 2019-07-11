package com.linty.engineeringidea.model

/**
 * UploadImage is a model class for upload image
 * @param image base64/gif/video string
 * @param type type of the upload data(base64/gif/video)
 * @param name the name of the image
 * @param title the title of the image
 */
data class UploadImage(
    val image: String, val type: String,
    val name: String, val title: String
)