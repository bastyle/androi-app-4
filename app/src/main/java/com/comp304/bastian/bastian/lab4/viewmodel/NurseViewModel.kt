package com.comp304.bastian.bastian.lab4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.database.NurseEntity
import com.comp304.bastian.bastian.lab4.repo.MedicalRepo
import kotlinx.coroutines.launch

class NurseViewModel () : ViewModel() {
    //private val _profileStateFlow = MutableStateFlow(emptyList<Profile>())
    private lateinit var database: MedicalDatabase
    private lateinit var repo: MedicalRepo
    //val profileStateFlow: StateFlow<List<Profile>> = _profileStateFlow.asStateFlow()

    private val _userLiveData = MutableLiveData<List<NurseEntity>>()
    val userLiveData: LiveData<List<NurseEntity>> = _userLiveData

    fun getData() {
        viewModelScope.launch {
            val nursesList = repo.getAllNurses()
            /*if (profileList != null) {
                _profileStateFlow.update {
                    profileList
                }
            }*/
        }
    }

    /*fun getDataByFirstName(firstName: String) {
        viewModelScope.launch {
            _userLiveData.postValue(repo.getUserByFirstName(firstName))
        }
    }*/

    fun initDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = MedicalRepo(database)
    }
}