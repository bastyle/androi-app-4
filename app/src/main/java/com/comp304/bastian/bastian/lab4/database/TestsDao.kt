package com.comp304.bastian.bastian.lab4.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun upsertAll(testsList: List<TestEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(nurse: TestEntity)

    @Query(
        """
            SELECT * FROM tests WHERE patientId=:patientId ORDER BY testId ASC
        """
    )
    fun getAllTestsByPatientId(patientId: Int): List<TestEntity>


    @Query(
        """
            SELECT * FROM tests ORDER BY testId DESC
        """
    )
    fun getAllTests(): List<TestEntity>

    @Query(
        """
            SELECT count(*) FROM tests            
        """
    )
    fun count():Int
}