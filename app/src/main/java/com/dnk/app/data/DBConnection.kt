package com.dnk.app.data

import CharacterEntity
import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CharacterEntity::class], version = 1)
abstract class DBConnection : RoomDatabase() {

    abstract fun characterDao(): CharacterDAO

    companion object {

        fun getDatabase(application: Application): DBConnection {
            return Room.databaseBuilder(
                application,
                DBConnection::class.java,
                "character_database"
            ).allowMainThreadQueries().build()
        }
    }

}