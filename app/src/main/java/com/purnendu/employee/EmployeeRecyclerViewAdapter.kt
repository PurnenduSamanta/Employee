package com.purnendu.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.purnendu.employee.databinding.SingleEmployeeDataItemBinding
import com.purnendu.employee.roomDb.EmployeeModel

class EmployeeRecyclerViewAdapter :
    RecyclerView.Adapter<EmployeeRecyclerViewAdapter.MyViewHolder>() {

    private var employeeList: List<EmployeeModel> = emptyList()

    fun setEmployees(list: List<EmployeeModel>) {
        this.employeeList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleEmployeeDataItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.employeeNo.text=employeeList[position].employeeNo.toString()
        holder.binding.employeeSalary.text=employeeList[position].employeeSalary.toString()
        holder.binding.fullName.text=employeeList[position].employeeName
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }


    class MyViewHolder(val binding: SingleEmployeeDataItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}