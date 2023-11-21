package com.comp304.bastian.bastian.lab4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NurseEntity::class, PatientEntity::class,TestEntity::class], version = 1)
abstract class NurseSystemDB:RoomDatabase() {
    abstract fun nurseDao(): NurseDao
    abstract fun patientDao(): PatientDao

    companion object {
        private var instance: NurseSystemDB? = null

        fun getInstance(context: Context): NurseSystemDB {
            if (instance == null) {
                synchronized(NurseSystemDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NurseSystemDB::class.java,
                        "NurseSystemDB"
                    ).build()
                }
            }
            return instance!!
        }
    }
}