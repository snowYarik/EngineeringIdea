package com.linty.engineeringidea

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.linty.engineeringidea.model.ImageResponse
import java.lang.reflect.Type

class ImageResponseDeserializer : JsonDeserializer<ImageResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ImageResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}