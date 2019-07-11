package com.linty.engineeringidea

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class Link(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val link: String

) {
    @Ignore
    constructor() : this(null, "")


}