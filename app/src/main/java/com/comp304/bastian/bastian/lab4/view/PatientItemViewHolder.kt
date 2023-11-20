package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.util.Log
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.databinding.PatientItemViewHolderBinding

class PatientItemViewHolder(private val binding: PatientItemViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

    private var idPatient = String()
    companion object{
        const val TAG="PatientItemViewHolder"
    }

    fun bind(item: PatientEntity, ctx: Context) {
        Log.e(TAG," binding item: ".plus(item.id.toString()))
        idPatient=item.id.toString()
        binding.patientIdTextView.text="Id Patient: "+ idPatient
        binding.patientNameTextView.text="Patient: "+item.firstName+" "+item.lastName
        binding.patientRoomTextView.text="Room: "+item.room
        binding.patientImageView.setImageResource(R.drawable.patient)
        binding.patientNurseTextView.text="Nurse Assigned: "+item.nurseId
        binding.testsButton.setOnClickListener {
            Log.e(TAG,"idPatient: "+idPatient)
            // go to test activity
        }
    }
}