package com.github.luizredig.dungeons_n_kotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.luizredig.dungeons_n_kotlin.data.entities.Entity1
import com.github.luizredig.dungeons_n_kotlin.data.entities.Entity2
import com.github.luizredig.dungeons_n_kotlin.data.entities.JoinEntity
import com.github.luizredig.dungeons_n_kotlin.data.dao.Entity1DAO
import com.github.luizredig.dungeons_n_kotlin.data.dao.Entity2DAO
import com.github.luizredig.dungeons_n_kotlin.data.dao.JoinEntityDAO

@Database(entities = [Entity1::class, Entity2::class, JoinEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entity1Dao(): Entity1DAO
    abstract fun entity2Dao(): Entity2DAO
    abstract fun joinEntityDao(): JoinEntityDAO
}
