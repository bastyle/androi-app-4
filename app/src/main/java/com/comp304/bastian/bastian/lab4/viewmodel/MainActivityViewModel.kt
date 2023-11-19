package com.comp304.bastian.bastian.lab4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.repo.MedicalRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private lateinit var database: MedicalDatabase
    private lateinit var repo: MedicalRepo

    private val _nurseStateFlow = MutableStateFlow(emptyList<NurseEntity>())
    val nurseStateFlow: StateFlow<List<NurseEntity>> = _nurseStateFlow.asStateFlow()

    private val _userLiveData = MutableLiveData<List<NurseEntity>>()
    val userLiveData: LiveData<List<NurseEntity>> = _userLiveData

    fun getAllNurses() {
        viewModelScope.launch {
            val nursesList = repo.getAllNurses()
            if (nursesList != null) {
                _nurseStateFlow.update {
                    nursesList
                }
            }
        }
    }

    fun signUpNurses(){
        viewModelScope.launch {
            repo.signUpNurses()
        }
    }

    fun initDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = MedicalRepo(database)
    }

}