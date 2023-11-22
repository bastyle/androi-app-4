package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.database.TestEntity
import com.comp304.bastian.bastian.lab4.databinding.TestItemViewHolderBinding


class TestsItemViewHolder(private val binding: TestItemViewHolderBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    private var idPatient = String()

    companion object {
        const val TAG = "TestsItemViewHolder"
    }

    fun bind(item: TestEntity, ctx: Context) {
        Log.e(TAG, " binding item: ".plus(item.id.toString()))
        idPatient = item.id.toString()
        binding.textViewTestId.text="Test ID: "+item.id.toString()
        binding.textViewNurseId.text="Nurse: "+item.nurseId
        binding.textViewBPL.text="BPL: "+item.BPL
        binding.textViewBPH.text="BPH: "+item.BPH
        binding.textViewTemperature.text="Temperature: "+item.temperature
        binding.textViewPatientId.text="Patient ID: "+item.patientId
        binding.textViewDepartment.text="Department: "+item.department
        binding.textViewUrine.text="Urine: "+item.urine
        binding.textViewXRay.text="XRay: "+item.xRay

        /*binding..setOnClickListener {
            Log.e(TAG, "tests button: " + idPatient)

            val intent = Intent(context, TestsActivity::class.java)
            intent.putExtra(TestsActivity.ID_PATIENT_KEY, idPatient)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }*/
    }
}