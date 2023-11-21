package com.comp304.bastian.bastian.lab4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.repo.MedicalRepo
import com.comp304.bastian.bastian.lab4.repo.PatientRepo
import kotlinx.coroutines.launch

class AddPatientViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: PatientRepo



    fun getNurseById(nurseId:String){
        viewModelScope.launch {
            repo.getNurseById(nurseId)
        }
    }

    fun setDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = PatientRepo(database)
    }
}