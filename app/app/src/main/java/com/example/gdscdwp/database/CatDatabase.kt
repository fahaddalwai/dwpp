package com.example.gdscdwp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gdscdwp.domain.CatImage

@Database(entities = [DatabaseCatImage::class], version = 3, exportSchema = false)
abstract class CatDatabase : RoomDatabase() {

    abstract val catDatabaseDao: CatDatabaseDao

    companion object {           //companion object is used to  access the methods for creating or getting the database without instantiating the class

        @Volatile                  //value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory
        private var INSTANCE: CatDatabase? = null          //keep a reference to the database, when one has been created

        fun getInstance(context: Context): CatDatabase {
            synchronized(this) {                   //means that only one thread of execution at a time can enter this block of code, which makes sure the database only gets initialized once.
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java,
                        "Cat_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}