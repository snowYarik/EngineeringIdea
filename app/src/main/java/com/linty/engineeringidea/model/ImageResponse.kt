package com.linty.engineeringidea.model

/**
 * ImageResponse is a model class for image response
 * @param success is success
 * @param status status code
 * @param data nested data
 */
data class ImageResponse(
    val success: Boolean,
    val status: Int,
    val data: UploadedImage
) {
    /**
     * UploadedImage is nested class
     * @param id image id
     * @param title image title
     * @param description image description
     * @param type image type(base64/gif/video)
     * @param animated is animated
     * @param width image width
     * @param height image height
     * @param size image size
     * @param views count of the image views
     * @param bandwidth ?
     * @param vote count of the image vote
     * @param favorite is favourite image
     * @param account_url account url
     * @param deletehash ?
     * @param name image name
     * @param link image link
     *
     */
    class UploadedImage(
        val id: String,
        val title: String,
        val description: String,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Int,
        val views: Int,
        val bandwidth: Int,
        val vote: String,
        val favorite: Boolean,
        val account_url: String,
        val deletehash: String,
        val name: String,
        val link: String
    )
}