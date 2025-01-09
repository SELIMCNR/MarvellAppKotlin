package com.example.marvelmobileapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.marvelmobileapp.model.MarvelData

@Database(entities = [MarvelData::class], version = 1)
abstract class MarvelDatabase : RoomDatabase(){
    abstract fun marvelDao():MarvelDAO

    companion object{
        @Volatile
        private var instance :MarvelDatabase? =null

        private val lock = Any()

        operator fun invoke (context: Context) = instance?: synchronized(lock){
            instance?:databaseOlustur(context).also{
                instance = it

            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MarvelDatabase::class.java,"marveldatabase"
        )  .build()




        /*
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Örneğin bir sütun ekliyorsanız:
                database.execSQL("ALTER TABLE MarvelData ADD COLUMN new_column_name TEXT")
            }
        }*/

    }
}