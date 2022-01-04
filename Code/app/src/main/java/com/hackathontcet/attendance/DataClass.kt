package com.hackathontcet.attendance

data class Subjects(var subjectImage: Int, var subjectName: String){
    /* It has two variables: subjectImage and subjectName for setting the Image and Name of the particular subject respectively */
}

data class Name(val rid: Int, val name: String,val absent: String )