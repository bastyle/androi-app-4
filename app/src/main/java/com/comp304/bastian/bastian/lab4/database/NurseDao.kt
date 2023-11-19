package com.comp304.bastian.bastian.lab4.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NurseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsertAll(userList: List<NurseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(nurse: NurseEntity)

    @Query(
        """
            SELECT * FROM nurses
        """
    )
    fun getAllNurses(): List<NurseEntity>

    @Query("SELECT * FROM nurses")
    fun getAllNurses2(): Flow<List<NurseEntity>>

    @Query(
        "SELECT * FROM nurses WHERE nurseId = :id"
    )
    fun getUserById(id: Int): NurseEntity

    @Query(
        """
            SELECT * FROM nurses
            WHERE firstName = :firstName
        """
    )
    fun getNurseByFirstName(firstName: String): List<NurseEntity>

    @Query(
        """
            SELECT * FROM nurses
            WHERE nurseId = :nurseId
        """
    )
    fun getNurseById(nurseId: Int): NurseEntity

    @Delete
    fun deleteUser(user: NurseEntity)
}