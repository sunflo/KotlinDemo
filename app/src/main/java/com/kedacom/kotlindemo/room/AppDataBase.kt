package com.kedacom.kotlindemo.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author huangxz
 */
@Database(entities = [User::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}