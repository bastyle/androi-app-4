package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalRepo(private val database: MedicalDatabase) {


    suspend fun getNurseByFirstName(firstName: String) : List<NurseEntity> {
        return withContext(Dispatchers.IO) {
            database.nurseDao().getNurseByFirstName(firstName)
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
                nurses.add(NurseEntity(id = "c.williams", firstName = "Catherine", lastName = "Williams", department = "Cardiology", password = "123" ))
                nurses.add(NurseEntity(id = "e.petrov", firstName = "Elena", lastName = "Petrov", department = "Obstetrics", password = "123" ))
                database.nurseDao().upsertAll(nurses)
                //database.nurseDao().insert(NurseEntity(id = 1, firstName = "Mary", lastName = "Perez", department = "dep 1", password = "123" ))
            }else{
                Log.e("Repo","nurses already exist.")
            }
        }
    }


}