package com.comp304.bastian.bastian.lab4.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nurses")
data class NurseEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("nurseId") val id: String,
    @ColumnInfo("firstName") val firstName: String,
    @ColumnInfo("lastName") val lastName: String,
    @ColumnInfo("department") val department: String,
    @ColumnInfo("password") val password: String
)