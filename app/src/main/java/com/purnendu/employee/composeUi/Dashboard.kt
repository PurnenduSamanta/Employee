package com.purnendu.employee.composeUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController) {


    Box(
        modifier = Modifier.fillMaxSize(),
    )
    {

        Text(
            modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.TopCenter),
            text = "Dashboard",
            fontSize = 50.sp
        )


        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                border = BorderStroke(1.dp,Color.Gray),
                onClick = {
                    navController.navigate("createEmployee")
                }) {

                Text(text = "Create Employee",color = Color.Blue)

            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                border = BorderStroke(1.dp,Color.Gray),
                onClick = {
                    navController.navigate("listEmployees")}) {
                Text(text = "List Employee", color = Color.Blue)
            }


        }
    }

}
