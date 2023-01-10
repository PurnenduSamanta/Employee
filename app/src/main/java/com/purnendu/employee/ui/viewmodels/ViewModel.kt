package com.purnendu.employee.ui.viewmodels

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purnendu.employee.Repository
import com.purnendu.employee.roomDb.EmployeeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(private val repository: Repository) : ViewModel() {

    fun insertEmployee(data: EmployeeModel) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertEmployee(data) }

    fun getEmployeeData(): LiveData<List<EmployeeModel>> = repository.getEmployeeData()


    fun validateInput(
        employeeNo: String?,
        employeeName: String?,
        employeeSalary: String?
    ): Boolean {
        if (employeeNo.isNullOrEmpty())
            return false
        if (!employeeNo.isDigitsOnly())
            return false
        if (employeeName.isNullOrEmpty())
            return false
        if (employeeSalary.isNullOrEmpty())
            return false
        if (!employeeSalary.isDigitsOnly())
            return false

        return true
    }

    fun isEmployeeNoExist(employeeNo: String,callback:(Boolean)->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.getCountOfEmployeeNo(employeeNo.toLong())
            val isExist= if (count > 0L)
                false
            else if (count == 0L)
                true
            else if (count == -1L)
                false
            else
                false
            callback(isExist)
        }
    }

    fun deleteEmployee(employeeNo:Long)=viewModelScope.launch(Dispatchers.IO) { repository.deleteEmployee(employeeNo) }

    fun updateEmployee(date:EmployeeModel)=viewModelScope.launch(Dispatchers.IO) { repository.updateEmployee(date) }
}

