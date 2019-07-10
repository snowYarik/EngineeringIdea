package com.linty.engineeringidea

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Link(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val link: String
)