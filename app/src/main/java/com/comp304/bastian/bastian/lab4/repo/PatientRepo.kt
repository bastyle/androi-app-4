package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PatientRepo(private val database: MedicalDatabase) {


    suspend fun getNurseById(nurseId: String) : NurseEntity {
        return withContext(Dispatchers.IO) {
            Log.e("PatientRepo","getNurseById "+nurseId)
            database.nurseDao().getNurseById(nurseId)
        }
    }

    suspend fun saveNewPatient(patient: PatientEntity) {
        return withContext(Dispatchers.IO) {
            Log.e("PatientRepo","saveNewPatient: "+patient.firstName)
            database.patientDao().save(patient)
        }
    }

    suspend fun getAllNurseIds() : List<String> {
        Log.e("PatientRepo","getAllNurseIds")
        return withContext(Dispatchers.IO) {
            database.nurseDao().getAllNurseIds()
        }
    }

    suspend fun getPatientById(patientId:Int) : PatientEntity {
        Log.e("PatientRepo","getPatientById $patientId")
        return withContext(Dispatchers.IO) {
            database.patientDao().getPatientById(patientId)
        }
    }

}