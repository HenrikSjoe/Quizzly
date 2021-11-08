package com.example.quizzly

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighScoreDao {

    @Insert
    fun insert(highScore: HighScore)

    @Delete
    fun delete(highScore: HighScore)

    @Query("SELECT * FROM highscore_table")
    fun getAll(): List<HighScore>
}