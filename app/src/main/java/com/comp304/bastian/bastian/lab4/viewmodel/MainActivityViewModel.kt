package com.comp304.bastian.bastian.lab4.viewmodel

import android.util.Log
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

    private val _nurseLiveData = MutableLiveData<List<NurseEntity>>()
    val nurseLiveData: LiveData<List<NurseEntity>> = _nurseLiveData

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    private val _loginMessage = MutableLiveData<String?>()
    val loginMessage : MutableLiveData<String?> get()= _loginMessage

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

    fun login(username: String, password: String) {
        viewModelScope.launch {
            //Log.e()
            val user = repo.getNurseByIdPass(username, password)
            if(user!==null){
                _navigateToHome.value = true
            }else{
                _navigateToHome.value = false
                _loginMessage.value="Invalid username or password"
            }
            //_navigateToHome.value = user != null
        }
    }

    fun onNavigationHandled() {
        _navigateToHome.value = false
        _loginMessage.value=null
    }

    fun initDatabase(medicalDatabase: MedicalDatabase) {
        database = medicalDatabase
        repo = MedicalRepo(database)
    }

}