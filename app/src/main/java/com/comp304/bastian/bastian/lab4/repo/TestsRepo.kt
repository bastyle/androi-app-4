package com.comp304.bastian.bastian.lab4.repo

import android.util.Log
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.TestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestsRepo(private val database: MedicalDatabase) {

    companion object{
        private const val TAG="TestsRepo"
    }

    suspend fun getAllTestsByPatientId(nurseId: Int) : List<TestEntity> {
        return withContext(Dispatchers.IO) {
            Log.e(TAG,"getAllTestsByPatientId "+nurseId)
            database.testsDao().getAllTestsByPatientId(nurseId)
        }
    }

    suspend fun getAllTests() : List<TestEntity> {
        return withContext(Dispatchers.IO) {
            Log.e(TAG,"getAllTests")
            database.testsDao().getAllTests()
        }
    }

    suspend fun saveNewTest(test: TestEntity) {
        return withContext(Dispatchers.IO) {
            Log.e(TAG,"saveNewTest p.id: "+test.patientId)
            database.testsDao().insert(test)
        }
    }

    suspend fun addDefaultTests(){
        return withContext(Dispatchers.IO) {
            Log.e(TAG,"aadingDefaultTests...")
            val counter = database.testsDao().count()
            if(counter==null || counter ==0){
                var tests= ArrayList<TestEntity>()
                tests.add(TestEntity(patientId = 1, nurseId = "m.perez", department = "Pediatrics", BPH = 2.5f, BPL = 3.5f, temperature = 36.8f, urine = "Negative", xRay = "Suspicious mass detected"))
                tests.add(TestEntity(patientId = 2, nurseId = "e.rodriguez", department = "Emergency Room", BPH = 1.5f, BPL = 2.5f, temperature = 38.8f, urine = "Negative", xRay = "No abnormalities"))
                tests.add(TestEntity(patientId = 3, nurseId = "m.tapia", department = "Cardiology", BPH = 2.8f, BPL = 3.5f, temperature = 39.2f, urine = "Negative", xRay = "No abnormalities"))
                tests.add(TestEntity(patientId = 3, nurseId = "e.rodriguez", department = "Obstetrics", BPH = 1.6f, BPL = 2.5f, temperature = 36.2f, urine = "Positive for infection", xRay = "No abnormalities"))
                tests.add(TestEntity(patientId = 4, nurseId = "m.tapia", department = "Pediatrics", BPH = 1.2f, BPL = 1.3f, temperature = 39.5f, urine = "Positive for infection", xRay = "Suspicious mass detected"))
                database.testsDao().upsertAll(tests)
            }else{
                Log.e(TAG,"default tests already exist.")
            }
        }
    }

}