package com.purnendu.employee.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface EmployeeDao {

    @Insert
    suspend fun insertEmployee(data: EmployeeModel)


    @Query("SELECT * FROM `Employee`")
    fun getAllEmployees(): LiveData<List<EmployeeModel>>


    @Query("SELECT COUNT(employeeNo) FROM `Employee` where employeeNo=:employeeNo")
    suspend fun getCountOfEmployeeNo(employeeNo:Long):Long



}