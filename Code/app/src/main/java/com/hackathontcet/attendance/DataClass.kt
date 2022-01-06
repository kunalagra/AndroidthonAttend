package com.hackathontcet.attendance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Subjects(var subjectImage: Int, var subjectName: String){
    /* It has two variables: subjectImage and subjectName for setting the Image and Name of the particular subject respectively */
}

data class Name(val rid: Int, val name: String,val absent: String )

@Entity(tableName="devtable")
data class Devdetails(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name="name")
    val name: String

    )
@Entity(tableName="devtable")
data class NameDetails(
    @ColumnInfo(name="name")
    val name: String
)
