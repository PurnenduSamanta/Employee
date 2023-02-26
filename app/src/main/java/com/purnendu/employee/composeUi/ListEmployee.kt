package com.purnendu.employee.composeUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.purnendu.employee.R
import com.purnendu.employee.Repository
import com.purnendu.employee.roomDb.EmployeeDb
import com.purnendu.employee.ui.viewmodels.ViewModel
import com.purnendu.employee.ui.viewmodels.ViewModelFactory


@Composable
fun ListEmployee(navController: NavController) {

    val repository = Repository(EmployeeDb.getDataBase(LocalContext.current))
    val viewModel: ViewModel = viewModel(factory = ViewModelFactory(repository))

    val employeeList by viewModel.getEmployeeData().observeAsState()


    if (employeeList == null)
        return


    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 40.dp),
            text = "Employee List",
            fontSize = 40.sp
        )

        if (employeeList!!.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_data),
                    contentDescription = "noItems"
                )
            }
        }

        else
        {
            Spacer(modifier = Modifier.height(5.dp))

            Divider(
                thickness = 1.dp,
                color = Color.Gray,
                modifier = Modifier
                    .alpha(0.3f)
                    .fillMaxWidth()
            )


            LazyColumn()
            {
                itemsIndexed(items = employeeList!!)
                { index, item ->
                    SingleEmployeeComposable(
                        name = item.employeeName,
                        salary = item.employeeSalary,
                        no = item.employeeNo,
                        onDelete = { viewModel.deleteEmployee(it) },
                        onUpdate = {
                            navController.navigate("updateEmployee/${item.employeeNo}/${item.employeeName}/${item.employeeSalary}")
                        }
                    )
                    if (index != employeeList!!.lastIndex)
                        Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

}


@Composable
fun SingleEmployeeComposable(
    name: String, salary: Int, no: Long,
    onDelete: (employeeNo: Long) -> Unit,
    onUpdate: () -> Unit
) {

    Card(
        elevation = 2.dp,
        shape = RectangleShape
    ) {

        var mDisplayMenu by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(End),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    modifier = Modifier,
                    text = no.toString(),
                    fontSize = 20.sp
                )
                Icon(
                    modifier = Modifier.clickable {
                        mDisplayMenu = true
                    },
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "menu"
                )

            }

            Box(modifier = Modifier.align(End))
            {
                DropdownMenu(
                    expanded = mDisplayMenu,
                    onDismissRequest = { mDisplayMenu = false }
                ) {

                    DropdownMenuItem(onClick = {
                        onDelete(no)
                        mDisplayMenu = false
                    }) {
                        Text(text = "Delete")
                    }

                    DropdownMenuItem(onClick = {
                        onUpdate()
                        mDisplayMenu = false
                    }) {
                        Text(text = "Update")
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically
            ) {

                Text(
                    text = "Name: ",
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = name)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically
            ) {

                Text(
                    text = "Salary: ",
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = salary.toString())
            }

        }

    }

}