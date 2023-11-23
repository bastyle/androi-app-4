package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.MedicalDatabase
import com.comp304.bastian.bastian.lab4.databinding.ActivityHomeBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientsBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil
import com.comp304.bastian.bastian.lab4.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class PatientsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientsBinding
    private lateinit var database: MedicalDatabase
    //private lateinit var database: NurseSystemDB
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var adapter: PatientsActivityViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("PatientsActivity","oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityPatientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = MedicalDatabase.getInstance(baseContext)
        viewModel.initDatabase(database)
        //load patients
        viewModel.getAllPatients()

        adapter = PatientsActivityViewAdapter(baseContext)
        binding.textNurseId.text="Nurse: "+ (GlobalUtil.getSharedPrefStr(this,GlobalUtil.NURSE_ID_KEY)
            ?.uppercase() ?: "")
        binding.recyclerView.adapter=this.adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        lifecycleScope.launch {
            viewModel.patientStateFlow.collect {
                adapter.updateList(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("Home","onCreateOptionsMenu..........")
        menuInflater.inflate(R.menu.menu_main,menu)
        //return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                GlobalUtil.logout(baseContext,this)
                return true
            }
            R.id.addPatientButton->{

                val intent = Intent(this, AddEditPatientActivity::class.java)

                startActivity(intent)
                return true
            }
            R.id.home->{

            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
            return true
        }
            // Add other cases if needed
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Home","onRestart..........")
        viewModel.getAllPatients()
    }
}