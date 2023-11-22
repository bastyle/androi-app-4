package com.comp304.bastian.bastian.lab4.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comp304.bastian.bastian.lab4.database.TestEntity
import com.comp304.bastian.bastian.lab4.databinding.TestItemViewHolderBinding

class TestsActivityViewAdapter (private val context: Context): RecyclerView.Adapter<TestsItemViewHolder>() {

    private val patientsList = ArrayList<TestEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsItemViewHolder {
        val binding = TestItemViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TestsItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: TestsItemViewHolder, position: Int) {
        holder.bind(patientsList[position],context)
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun updateList(items:List<TestEntity>){
        val size=patientsList.size
        patientsList.clear()
        notifyItemRangeRemoved(0,size)
        patientsList.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }
}