package com.linty.engineeringidea.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Link is the entity class for Link talbe of the database
 * @param id id of the link
 * @param link link strings
 */
@Entity
data class Link(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val link: String

) {
    @Ignore
    constructor() : this(null, "")


}