package com.linty.engineeringidea.network

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.linty.engineeringidea.model.UploadImage
import java.lang.reflect.Type

/**
 * UploadImageSerializer is a class serializer witch serialize UploadImage to Json
 */
class UploadImageSerializer : JsonSerializer<UploadImage> {
    override fun serialize(src: UploadImage?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val json = JsonObject()
        json.addProperty("image", src!!.image)
        json.addProperty("type", src.type)
        json.addProperty("name", src.name)
        json.addProperty("title", src.title)
        return json
    }
}