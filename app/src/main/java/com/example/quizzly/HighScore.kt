package com.example.quizzly

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "highscore_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "player") var player: String?,
    @ColumnInfo(name = "score") var score: Int
)