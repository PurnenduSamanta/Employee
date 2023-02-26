package com.purnendu.employee.composeUi

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun CreateEmployee(navController: NavController) {

    val repository = Repository(EmployeeDb.getDataBase(LocalContext.current))
    val viewModel: ViewModel = viewModel(factory = ViewModelFactory(repository))


    val employeeName = remember {
        mutableStateOf("")
    }

    val employeeNo = remember {
        mutableStateOf("")
    }

    val employeeSalary = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = "Create Employee",
            fontSize = 35.sp,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            placeHolder = "Employee Name",
            value = employeeName.value,
            keyboardType = KeyboardType.Text
        ) {
            employeeName.value = it.take(20)
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            placeHolder = "Employee No",
            value = employeeNo.value,
            keyboardType = KeyboardType.NumberPassword,
        ) {
            employeeNo.value = if (it.isDigitsOnly()) it.take(10) else ""
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            placeHolder = "Employee Salary",
            value = employeeSalary.value,
            keyboardType = KeyboardType.NumberPassword,
        ) {
            employeeSalary.value = if (it.isDigitsOnly()) it.take(8) else ""
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            onClick = {

                if (!viewModel.validateInput(
                        employeeNo.value,
                        employeeName.value,
                        employeeSalary.value
                    )
                ) {
                    Toast.makeText(context, "Bad Input", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                viewModel.isEmployeeNoExist(employeeNo.value) {

                    if (it) {
                        viewModel.insertEmployee(
                            EmployeeModel(
                                employeeNo = employeeNo.value.toLong(),
                                employeeName = employeeName.value,
                                employeeSalary = employeeSalary.value.toInt()
                            )
                        )
                        navController.navigate("dashboard")
                        {
                            popUpTo("dashboard") {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, "Employee No exist already", Toast.LENGTH_SHORT).show()
                    }
                }

            },
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, Color.Gray),
        ) {
            Text(text = "Create", color = Color.Blue)
        }
    }
}