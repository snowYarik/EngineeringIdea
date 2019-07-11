package com.linty.engineeringidea.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.linty.engineeringidea.model.Link

/**
 * AppDatabase is a class for database connection
 * */
@Database(entities = [Link::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * linkDao is a abstract method for getting Link DAO
     * @return object for dao connection
     */
    abstract fun linkDao(): LinkDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         *Method for getting database
         * Singleton
         * @return database class
         */
        fun getDB(context: Context): AppDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "eng_idea_db"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}