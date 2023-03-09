package com.crazy.migrationexample.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.crazy.migrationexample.database.PersonDao
import com.crazy.migrationexample.database.migrationexampledb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseInjection {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): migrationexampledb {
        val room = Room.databaseBuilder(
            context,
            migrationexampledb::class.java,
            "migrationExample"
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        return room.build()
    }

    @Singleton
    @Provides
    fun getPersonDao (database:migrationexampledb): PersonDao = database.getPersonDao()

    private val MIGRATION_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
        }
    }

    private val MIGRATION_2_3 = object : Migration (2,3){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
                create table 'person_table_dummy'(
                 'name' text not null,'clas' Integer not null,'status' text,'roll' integer not null ,'parent' text not null default "father",primary key( 'name','clas','roll','parent'));
            """.trimIndent())
            database.execSQL("""
                insert into 'person_table_dummy'( 'name','clas','status','roll') select 'name','clas','status','roll' from 'person_table';
            """.trimIndent())
            database.execSQL("""
                drop table 'person_table';
            """.trimIndent())
            database.execSQL(
                """
                    alter table person_table_dummy rename to person_table;
                """.trimIndent()
            )
        }
    }

}