package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comp304.bastian.bastian.lab4.database.PatientEntity
import com.comp304.bastian.bastian.lab4.databinding.PatientItemViewHolderBinding

class PatientsActivityViewAdapter (private val context: Context): RecyclerView.Adapter<PatientItemViewHolder>() {

    private val patientsList = ArrayList<PatientEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientItemViewHolder {
        val binding = PatientItemViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PatientItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: PatientItemViewHolder, position: Int) {
        holder.bind(patientsList[position],context)
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun updateList(patients:List<PatientEntity>){
        val size=patientsList.size
        patientsList.clear()
        notifyItemRangeRemoved(0,size)
        patientsList.addAll(patients)
        notifyItemRangeInserted(0, patients.size)
    }


}