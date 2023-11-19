package com.comp304.bastian.bastian.lab4.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NurseEntity::class, PatientEntity::class,TestEntity::class], version = 1)
abstract class MedicalDatabase:RoomDatabase() {
    abstract fun nurseDao(): NurseDao
}