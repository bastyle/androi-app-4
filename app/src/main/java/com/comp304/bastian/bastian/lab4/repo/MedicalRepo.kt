package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalRepo(private val database: MedicalDatabase) {


    suspend fun getUserByFirstName(firstName: String) : List<NurseEntity> {
        return withContext(Dispatchers.IO) {
            database.nurseDao().getNurseByFirstName(firstName)
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
            val nurse = database.nurseDao().getNurseById(1)
            if(nurse==null){
                var nurses= ArrayList<NurseEntity>()
                nurses.add(NurseEntity(id = 1, firstName = "Mary", lastName = "Perez", department = "Obstetrics", password = "123" ))
                nurses.add(NurseEntity(id = 2, firstName = "Emily", lastName = "Rodriguez", department = "Pediatrics", password = "123" ))
                nurses.add(NurseEntity(id = 3, firstName = "Michale", lastName = "Tapia", department = "Emergency Room", password = "123" ))
                nurses.add(NurseEntity(id = 4, firstName = "Catherine", lastName = "Williams", department = "Cardiology", password = "123" ))
                nurses.add(NurseEntity(id = 5, firstName = "Elena", lastName = "Petrov", department = "Obstetrics", password = "123" ))
                database.nurseDao().upsertAll(nurses)
                //database.nurseDao().insert(NurseEntity(id = 1, firstName = "Mary", lastName = "Perez", department = "dep 1", password = "123" ))
            }else{
                Log.e("Repo","nurses already exist.")
            }
        }
    }


}