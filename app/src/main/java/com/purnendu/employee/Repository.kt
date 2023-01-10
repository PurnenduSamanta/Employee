package com.purnendu.employee

import androidx.lifecycle.LiveData
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.roomDb.EmployeeModel

class Repository(database:EmployeeDb)
{
    private  val dao=database.EmployeeDao()

    suspend fun insertEmployee(data:EmployeeModel)=dao.insertEmployee(data)

    fun getEmployeeData():LiveData<List<EmployeeModel>> =dao.getAllEmployees()

    suspend fun getCountOfEmployeeNo(employeeNo:Long): Long = dao.getCountOfEmployeeNo(employeeNo)

    suspend fun deleteEmployee(employeeNo:Long)= dao.deleteEmployee(employeeNo)

    suspend fun updateEmployee(data:EmployeeModel)= dao.updateEmployee(data)

}