package com.comp304.bastian.bastian.lab4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.TestEntity
import com.comp304.bastian.bastian.lab4.repo.TestsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TestsViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: TestsRepo

    private val _testsStateFlow = MutableStateFlow(emptyList<TestEntity>())
    val testsIdStateFlow: StateFlow<List<TestEntity>> = _testsStateFlow.asStateFlow()


    fun getAllTestsByPatientId(id:Int){
        viewModelScope.launch {
            val testsList = repo.getAllTestsByPatientId(id)
            if (testsList != null) {
                _testsStateFlow.update {
                    testsList
                }
            }
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

    }

    fun setDatabase(medicalDatabase: MedicalDatabase, patientId:Int) {
        database = medicalDatabase
        repo = TestsRepo(database)
        addDefaultTests()
    }
}