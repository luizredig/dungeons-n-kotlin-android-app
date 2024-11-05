package com.dnk.app.data

import CharacterEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDAO {

    @Insert
    fun insert(character: CharacterEntity)

    @Update
    fun update(character: CharacterEntity)

    @Delete
    fun delete(character: CharacterEntity)

    @Query("SELECT * FROM character")
    fun getAll(): MutableList<CharacterEntity>

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Int): CharacterEntity?
}