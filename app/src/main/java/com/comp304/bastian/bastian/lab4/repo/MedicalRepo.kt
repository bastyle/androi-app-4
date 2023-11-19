package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class MedicalRepo(private val database: MedicalDatabase) {

   // private var defaultNurses:

    /*suspend fun getNurses() : List<NurseEntity>? {
        return withContext(Dispatchers.IO) {
            try {
                /*val service = ProfileService.service
                val data = service.getProfiles()
                database.nurseDao().upsertAll(data.users.toUserEntityList())
                data.toProfileList()*/
                return@withContext null
            } catch (ex: UnknownHostException) {
                return@withContext null
            } catch (ex: Exception) {
                Log.e("Repo", ex.message.toString())
                return@withContext null
            }
        }
    }*/

    suspend fun getUserByFirstName(firstName: String) : List<NurseEntity> {
        return withContext(Dispatchers.IO) {
            database.nurseDao().getNurseByFirstName(firstName)
        }
    }

    suspend fun getAllNurses() : List<NurseEntity> {
        return withContext(Dispatchers.IO) {
            database.nurseDao().getAllNurses()
        }
    }

    suspend fun defineNurses(){
        return withContext(Dispatchers.IO) {
            Log.e("Repo","defineNurses")
            var nurses= ArrayList<NurseEntity>()
            nurses.add(NurseEntity(id = 1, firstName = "nurse 1", lastName = "perez", department = "dep 1", password = "123" ))
            database.nurseDao().upsertAll(nurses)
        }

    }


}