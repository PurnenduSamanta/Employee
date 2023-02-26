package com.purnendu.employee.composeUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.purnendu.employee.Repository
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.roomDb.EmployeeModel
import com.purnendu.employee.ui.viewmodels.ViewModel
import com.purnendu.employee.ui.viewmodels.ViewModelFactory

@Composable
fun UpdateEmployee(
    navController: NavController,
    employeeNo: Long,
    name: String,
    salary: Int
) {


    val employeeName = remember {
        mutableStateOf(name)
    }

    val employeeSalary = remember {
        mutableStateOf(salary.toString())
    }

    val repository = Repository(EmployeeDb.getDataBase(LocalContext.current))
    val viewModel: ViewModel = viewModel(factory = ViewModelFactory(repository))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier,
            text = "Update Employee", fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            placeHolder = "Employee Name",
            value = employeeName.value,
            keyboardType = KeyboardType.Text
        ) {
            employeeName.value = it.take(20)
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            placeHolder = "Employee Salary",
            value = employeeSalary.value,
            keyboardType = KeyboardType.NumberPassword
        ) {
            employeeSalary.value = if (it.isDigitsOnly()) it.take(8) else ""
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.updateEmployee(
                    EmployeeModel(
                        employeeNo,
                        employeeName.value,
                        employeeSalary.value.toInt()
                    )
                )
                navController.popBackStack("dashboard",false)
            },
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
        ) {
            Text(text = "Update", color = Color.Blue)
        }

    }
}