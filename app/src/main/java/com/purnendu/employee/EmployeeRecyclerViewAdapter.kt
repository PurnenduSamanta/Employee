package com.purnendu.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.purnendu.employee.databinding.SingleEmployeeDataItemBinding
import com.purnendu.employee.roomDb.EmployeeModel

class EmployeeRecyclerViewAdapter(
    private val context: Context,
    private val onDeleteEmployee: (employeeNo: Long) -> Unit,
    private val onUpdateTask: (employeeNo: Long, employeeName: String, employeeSalary: Int) -> Unit
) :
    RecyclerView.Adapter<EmployeeRecyclerViewAdapter.MyViewHolder>() {

    private var employeeList: List<EmployeeModel> = emptyList()

    fun setEmployees(list: List<EmployeeModel>) {
        this.employeeList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleEmployeeDataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.employeeNo.text = employeeList[position].employeeNo.toString()
        holder.binding.employeeSalary.text = employeeList[position].employeeSalary.toString()
        holder.binding.fullName.text = employeeList[position].employeeName

        holder.binding.optionMenuIcon.setOnClickListener {
            createMenu(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }


    class MyViewHolder(val binding: SingleEmployeeDataItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private fun createMenu(holder: MyViewHolder, position: Int) {
        val popupMenu = PopupMenu(context, holder.binding.optionMenuIcon)
        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.delete -> {
                    onDeleteEmployee(employeeList[position].employeeNo)
                }
                R.id.update -> {
                    onUpdateTask(
                        employeeList[position].employeeNo,
                        employeeList[position].employeeName,
                        employeeList[position].employeeSalary
                    )
                }
            }
            true
        }
        popupMenu.show()
    }

}