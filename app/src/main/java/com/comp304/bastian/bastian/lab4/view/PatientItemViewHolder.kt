package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.comp304.bastian.bastian.lab4.R
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.databinding.PatientItemViewHolderBinding
import com.comp304.bastian.bastian.lab4.util.GlobalUtil

class PatientItemViewHolder(private val binding: PatientItemViewHolderBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

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
            Log.e(TAG,"tests button: "+idPatient)

            val intent = Intent(context, TestsActivity::class.java)
            intent.putExtra(GlobalUtil.ID_PATIENT_KEY, idPatient)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        binding.editPatientInfo.setOnClickListener {
            Log.e(TAG,"go to editPatientInfo: "+idPatient)
            val intent = Intent(context, AddEditPatientActivity::class.java)
            intent.putExtra(GlobalUtil.IS_EDITION, true)
            intent.putExtra(GlobalUtil.ID_PATIENT_KEY, idPatient.toInt())
            intent.putExtra(GlobalUtil.PATIENT_FNAME_KEY, item.firstName)
            intent.putExtra(GlobalUtil.PATIENT_LNAME_KEY, item.lastName)
            intent.putExtra(GlobalUtil.ROOM_NAME_KEY, item.room)
            intent.putExtra(GlobalUtil.NURSE_ID_KEY, item.nurseId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}