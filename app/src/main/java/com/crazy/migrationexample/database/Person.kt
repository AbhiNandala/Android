package com.crazy.migrationexample.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("person_table", primaryKeys = ["name","clas","roll"])
data class Person (
    val name: String,
    val clas : Int,
    val status : String?,
    val roll : Int,
//    @NonNull
//    val parent : String
)

