package com.kedacom.kotlindemo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

/**
 * @author huangxz
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user_info")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_info WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user_info WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}