package com.comp304.bastian.bastian.lab4.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsertAll(userList: List<PatientEntity>)

    @Query(
        """
            SELECT * FROM patients
        """
    )
    fun getAllPatients(): List<PatientEntity>


    @Query(
        """
            SELECT count(*) FROM patients            
        """
    )
    fun count():Int
}