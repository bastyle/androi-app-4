package com.comp304.bastian.bastian.lab4.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityTestsBinding
import com.comp304.bastian.bastian.lab4.viewmodel.TestsViewModel

class TestsActivity(private val patientId:Int): AppCompatActivity() {
    private lateinit var binding: ActivityTestsBinding
    private lateinit var database: MedicalDatabase
    private val viewModel: TestsViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Home", "oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        //database = Room.databaseBuilder(applicationContext, MedicalDatabase::class.java, "MedicalCentre").fallbackToDestructiveMigration().build()
        database = MedicalDatabase.getInstance(baseContext)
        viewModel.setDatabase(database, patientId)



    }
}