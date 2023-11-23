package com.comp304.bastian.bastian.lab4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.databinding.ActivityHomeBinding
import com.comp304.bastian.bastian.lab4.databinding.ActivityPatientsBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("HomeActivity", "oncreate..........")
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textNurseId.text="Nurse: "+ (GlobalUtil.getSharedPrefStr(this, GlobalUtil.NURSE_ID_KEY)
            ?.uppercase() ?: "")

        binding.buttonToPatients.setOnClickListener {
            val intent = Intent(this, PatientsActivity::class.java)
            startActivity(intent)
        }
        binding.buttonToTests.setOnClickListener {
            val intent = Intent(this, TestsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("HomeActivity","onCreateOptionsMenu..........")
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                GlobalUtil.logout(baseContext,this)
                return true
            }
            /*R.id.goBack->{
                finish()
                return true
            }*/
            // Add other cases if needed
            else -> return super.onOptionsItemSelected(item)
        }
    }
}