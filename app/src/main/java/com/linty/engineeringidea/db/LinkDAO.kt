package com.linty.engineeringidea.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.linty.engineeringidea.model.Link
import io.reactivex.Single

/**
 * This interface is DAO for link table of the database
 */
@Dao
interface LinkDAO {
    /**
     * Abstract method for selecting all links from table
     * @return list of Link class object Single type
     */
    @Query("Select * from link")
    fun getAllLinks(): Single<List<Link>>

    /**
     * Abstract method for inserting Link object
     * @return Long id
     */
    @Insert
    fun insertLink(link: Link): Long
}