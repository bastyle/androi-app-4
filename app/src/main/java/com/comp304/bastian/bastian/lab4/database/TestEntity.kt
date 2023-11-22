package com.comp304.bastian.bastian.lab4.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tests")
data class TestEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("testId") val id: Int = 0,
    @ColumnInfo("patientId") val patientId: Int,
    @ColumnInfo("nurseId") val nurseId:String,
    @ColumnInfo("department") val department: String,
    @ColumnInfo("BPL") val BPL: Float,
    @ColumnInfo("BPH") val BPH: Float,
    @ColumnInfo("temperature") val temperature: Float,
)