package com.purnendu.employee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.purnendu.employee.composeUi.*
import com.purnendu.employee.ui.theme.EmployeeTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EmployeeApp()
                }
            }
        }
    }
}

@Composable
fun EmployeeApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splashScreen") {
        composable(route = "splashScreen") {
            SplashScreen(navController)
        }

        composable(route = "dashboard") {
            Dashboard(navController)
        }

        composable(route = "createEmployee") {
            CreateEmployee(navController)
        }

        composable(route = "listEmployees") {
            ListEmployee(navController)
        }

        composable(
            route = "updateEmployee/{no}/{name}/{salary}",
            arguments = listOf(
                navArgument("no") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("salary") { type = NavType.IntType },
            )

        ) { backStackEntry ->
            val no = backStackEntry.arguments?.getLong("no")
            val name = backStackEntry.arguments?.getString("name")
            val salary = backStackEntry.arguments?.getInt("salary")

            if (no!=null && name != null && salary != null) {
                UpdateEmployee(
                    navController = navController,
                    employeeNo=no,
                    name = name, salary = salary
                )
            }
        }

    }
}
