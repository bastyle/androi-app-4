package com.comp304.bastian.bastian.lab4.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("patientId") val id: Int = 0,
    @ColumnInfo("firstName") val firstName: String,
    @ColumnInfo("lastName") val lastName: String,
    @ColumnInfo("nurseId") val nurseId: Int = 0,
    @ColumnInfo("room") val room: String,
)