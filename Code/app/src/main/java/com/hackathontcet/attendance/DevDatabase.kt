package com.hackathontcet.attendance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Devdetails::class], version = 1,exportSchema = false)
abstract class DevDatabase: RoomDatabase() {
    abstract fun devDao(): DevDAO
    companion object {
        @Volatile
        private var INSTANCE: DevDatabase? = null

        fun getDatabase(context: Context): DevDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DevDatabase::class.java,
                    "devdetails"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }
}