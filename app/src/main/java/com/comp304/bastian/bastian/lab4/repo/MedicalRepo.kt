package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalRepo(private val database: MedicalDatabase) {


    suspend fun getNurseById(nurseId: String) : NurseEntity {
        return withContext(Dispatchers.IO) {
            Log.e("Repo","getNurseById "+nurseId)
            database.nurseDao().getNurseById(nurseId)
        }
    }

    suspend fun getNurseByIdPass(id: String, pass: String) : NurseEntity {
        return withContext(Dispatchers.IO) {
            Log.e("Repo","getNurseByIdPass "+id+"  "+pass)
            database.nurseDao().getNurseByIdPass(id,pass)
        }
    }

    suspend fun getAllNurses() : List<NurseEntity> {
        Log.e("Repo","getAllNurses")
        return withContext(Dispatchers.IO) {
            database.nurseDao().getAllNurses()
        }
    }

    suspend fun signUpNurses(){
        return withContext(Dispatchers.IO) {
            Log.e("Repo","defineNurses")
            val nurseCount = database.nurseDao().countNurses()
            if(nurseCount==null || nurseCount ==0){
                var nurses= ArrayList<NurseEntity>()
                nurses.add(NurseEntity(id = "m.perez", firstName = "Mary", lastName = "Perez", department = "Obstetrics", password = "123" ))
                nurses.add(NurseEntity(id = "e.rodriguez", firstName = "Emily", lastName = "Rodriguez", department = "Pediatrics", password = "123" ))
                nurses.add(NurseEntity(id = "m.tapia", firstName = "Michale", lastName = "Tapia", department = "Emergency Room", password = "123" ))
                nurses.add(NurseEntity(id = "e.petrov", firstName = "Catherine", lastName = "Williams", department = "Cardiology", password = "123" ))
                nurses.add(NurseEntity(id = "e.petrov", firstName = "Elena", lastName = "Petrov", department = "Obstetrics", password = "123" ))
                database.nurseDao().upsertAll(nurses)
                //database.nurseDao().insert(NurseEntity(id = 1, firstName = "Mary", lastName = "Perez", department = "dep 1", password = "123" ))
            }else{
                Log.e("Repo","nurses already exist.")
            }
        }
    }

    suspend fun createDefaultPatients(){
        return withContext(Dispatchers.IO) {
            Log.e("Repo","createDefaultPatients")
            val counter = database.patientDao().count()
            if(counter==null || counter ==0){
                var patients= ArrayList<PatientEntity>()
                patients.add(PatientEntity(firstName = "Luciano", lastName = "Tapia", nurseId = "m.perez", room = "1110" ))
                patients.add(PatientEntity(firstName = "Emily", lastName = "Unzueta", nurseId = "e.rodriguez", room = "2508" ))
                patients.add(PatientEntity(firstName = "Natalia", lastName = "Ramirez", nurseId = "m.tapia", room = "1322" ))
                patients.add(PatientEntity(firstName = "Karina", lastName = "Ceveda", nurseId = "e.petrov", room = "1444" ))
                patients.add(PatientEntity(firstName = "Manuel", lastName = "Tijoux", nurseId = "e.petrov", room = "2569" ))
                database.patientDao().upsertAll(patients)
            }else{
                Log.e("Repo","patients already exist.")
            }
        }
    }

    // patients
    suspend fun getAllPatients() : List<PatientEntity> {
        Log.e("Repo","getAllPatients")
        return withContext(Dispatchers.IO) {
            database.patientDao().getAllPatients()
        }
    }

}