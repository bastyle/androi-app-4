package com.comp304.bastian.bastian.lab4.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.repo.PatientRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddPatientViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: PatientRepo

    private val _nursesStateFlow = MutableStateFlow(emptyList<String>())
    val nursesIdStateFlow: StateFlow<List<String>> = _nursesStateFlow.asStateFlow()


    fun getNurseById(nurseId:String){
        viewModelScope.launch {
            repo.getNurseById(nurseId)
        }
    }

    fun getNurseIds() {
        viewModelScope.launch {
            val nurseIds = repo.getAllNurseIds()
            Log.e("AddPatientViewModel","getNurseIds")
            if (nurseIds != null) {
                _nursesStateFlow.update {
                    nurseIds
                }
            }
        }
    }

    fun saveNewPatient(patient:PatientEntity){
        viewModelScope.launch {
            repo.saveNewPatient(patient)
        }
    }

    fun setDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = PatientRepo(database)
        getNurseIds()
    }


}