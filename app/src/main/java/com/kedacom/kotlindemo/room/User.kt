package com.kedacom.kotlindemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author huangxz
 */

@Entity(tableName = "user_info")
data class User(
        @PrimaryKey(autoGenerate = true)
        val uid: Int,
        @ColumnInfo
        val firstName: String,
        @ColumnInfo
        val lastName: String
)