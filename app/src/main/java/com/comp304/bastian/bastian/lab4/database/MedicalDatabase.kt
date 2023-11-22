package com.comp304.bastian.bastian.lab4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NurseEntity::class, PatientEntity::class,TestEntity::class], version = 3)
abstract class MedicalDatabase:RoomDatabase() {
    abstract fun nurseDao(): NurseDao
    abstract fun patientDao(): PatientDao
    abstract fun testsDao(): TestsDao

    companion object {
        private var instance: MedicalDatabase? = null

        fun getInstance(context: Context): MedicalDatabase {
            if (instance == null) {
                synchronized(MedicalDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicalDatabase::class.java,
                        "NurseSystemDB"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance!!
        }
    }
}