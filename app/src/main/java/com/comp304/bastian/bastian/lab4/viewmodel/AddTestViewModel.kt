package com.comp304.bastian.bastian.lab4.viewmodel

import android.util.Log
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

class AddTestViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: TestsRepo
    private lateinit var patientRepo: PatientRepo

    private val _nursesStateFlow = MutableStateFlow(emptyList<String>())
    val nursesIdStateFlow: StateFlow<List<String>> = _nursesStateFlow.asStateFlow()

    private val _patientsStateFlow = MutableStateFlow(emptyList<PatientEntity>())
    val patientsStateFlow: StateFlow<List<PatientEntity>> = _patientsStateFlow.asStateFlow()

    fun saveNewTest(test: TestEntity){
        viewModelScope.launch {
            repo.saveNewTest(test)
        }
    }

    private fun getNurses() {
        viewModelScope.launch {
            val nurseIds = patientRepo.getAllNurseIds()
            Log.e("AddTestViewModel","getNurses")
            if (nurseIds != null) {
                Log.e("AddTestViewModel","!= null")
                _nursesStateFlow.update {
                    nurseIds
                }
            }
        }
    }

    private fun getPatients() {
        viewModelScope.launch {
            val patientList = patientRepo.getAllPatients()
            Log.e("AddTestViewModel","getPatients")
            if (patientList != null) {
                Log.e("AddTestViewModel","!= null")
                _patientsStateFlow.update {
                    patientList
                }
            }
        }
    }

    fun setDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = TestsRepo(database)
        patientRepo = PatientRepo(database)
        getNurses()
        getPatients()
    }
}