package com.linty.engineeringidea

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface LinkDAO {
    @Query("Select * from link")
    fun getAllLinks(): Single<List<Link>>

    @Insert
    fun insertLink(link: Link): Long
}