package com.linty.engineeringidea.model

data class ImageResponse(
    var success: Boolean?,
    var status: Int?,
    var data: UploadedImage?
) {

    //TODO Builder pattern with data class
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
    ) {
        override fun toString(): String {
            return "UploadedImage(id='$id', title='$title', description='$description', type='$type', animated=$animated, width=$width, height=$height, size=$size, views=$views, bandwidth=$bandwidth, vote='$vote', favorite=$favorite, account_url='$account_url', deletehash='$deletehash', name='$name', link='$link')"
        }
    }
}