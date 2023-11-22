package com.comp304.bastian.bastian.lab4.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsertAll(userList: List<PatientEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(patient: PatientEntity)

    @Query(
        """
            SELECT * FROM patients ORDER BY patientId DESC
        """
    )
    fun getAllPatients(): List<PatientEntity>

    @Query(
        """
            SELECT * FROM patients WHERE patientId=:patientId
        """
    )
    fun getPatientById(patientId:Int): PatientEntity

    @Query(
        """
            SELECT count(*) FROM patients            
        """
    )
    fun count():Int
}