package com.purnendu.employee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Employee(
    val employeeNo: Long,
    val employeeName: String,
    val employeeSalary: Int
) : Parcelable