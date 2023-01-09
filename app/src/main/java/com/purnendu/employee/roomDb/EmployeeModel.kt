package com.purnendu.employee.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Employee")
data class EmployeeModel(
    @PrimaryKey
    val employeeNo: Long,
    val employeeName: String,
    val employeeSalary: Int,
)
