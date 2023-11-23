package com.comp304.bastian.bastian.lab4.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.database.TestEntity
import com.comp304.bastian.bastian.lab4.repo.PatientRepo
import com.comp304.bastian.bastian.lab4.repo.TestsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TestsViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: TestsRepo
    private lateinit var patientRepo: PatientRepo

    private val _testsStateFlow = MutableStateFlow(emptyList<TestEntity>())
    val testsIdStateFlow: StateFlow<List<TestEntity>> = _testsStateFlow.asStateFlow()

    private val _patient = MutableLiveData<PatientEntity>()
    val patient : MutableLiveData<PatientEntity> get()= _patient

    fun getAllTestsByPatientId(id:Int){
        viewModelScope.launch {
            val testsList = repo.getAllTestsByPatientId(id)
            Log.d("TestsViewModel","getAllTestsByPatientId size: "+testsList.size)
            if (testsList != null) {
                _testsStateFlow.update {
                    testsList
                }
            }
        }
    }


    public fun getAllTests(){
        viewModelScope.launch {
            val testsList = repo.getAllTests()
            Log.d("TestsViewModel","getAllTests size: "+testsList.size)
            if (testsList != null) {
                _testsStateFlow.update {
                    testsList
                }
            }
        }
    }

    fun getPatientInfo(patientId:Int) {
        viewModelScope.launch {
            /*val p = patientRepo.getPatientById(patientId)
            if (p != null) {
                _patient.value=p
            }*/
            //_patient.postValue(patientRepo.getPatientById(patientId))
            _patient.postValue(patientRepo.getPatientById(patientId))
        }
    }

    fun saveNewTest(test: TestEntity){
        viewModelScope.launch {
            repo.saveNewTest(test)
        }
    }


    fun addDefaultTests(){
        viewModelScope.launch {
            repo.addDefaultTests()
        }
    }

    fun setDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = TestsRepo(database)
        addDefaultTests()
        getAllTests()
    }

    fun setDatabase(medicalDatabase: MedicalDatabase, patientId:Int) {
        database = medicalDatabase
        repo = TestsRepo(database)
        patientRepo = PatientRepo(database)
        addDefaultTests()
        getAllTestsByPatientId(patientId)
        getPatientInfo(patientId)
    }
}