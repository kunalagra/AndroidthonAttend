package com.hackathontcet.attendance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DevDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (dev: Devdetails)
    @Query("SELECT * FROM devtable")
    fun getDetails() : LiveData<List<Devdetails>>
    @Query("SELECT name FROM devtable")
    fun getName() : LiveData<List<NameDetails>>
}