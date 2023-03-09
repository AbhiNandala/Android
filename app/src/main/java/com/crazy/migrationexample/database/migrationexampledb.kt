package com.crazy.migrationexample.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Person::class], version = 2, exportSchema = false)
abstract class migrationexampledb : RoomDatabase() {
    abstract fun getPersonDao() : PersonDao
}