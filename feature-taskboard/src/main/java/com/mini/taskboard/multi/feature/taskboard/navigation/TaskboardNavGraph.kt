package com.mini.taskboard.multi.feature.taskboard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mini.taskboard.multi.feature.taskboard.ui.AddEditTaskScreen
import com.mini.taskboard.multi.feature.taskboard.ui.TaskListScreen

sealed class TaskRoute(val route: String){
   object  List: TaskRoute("list")
    object AddEdit : TaskRoute("add_task")
    object Edit : TaskRoute("edit_task/{taskId}") {
        fun route(taskId: Long) = "edit_task/$taskId"
    }
}

@Composable
fun TaskboardNavGraph(modifier: Modifier = Modifier, startDestination: String = TaskRoute.List.route){
    val navController = rememberNavController()

    NavHost(navController, startDestination){
        composable(TaskRoute.List.route) {
            TaskListScreen(onNavigateToAdd = {
                navController.navigate(TaskRoute.AddEdit.route)
            },
                onNavigateToEdit = { id ->
                  navController.navigate(TaskRoute.Edit.route(id))
                })
        }

        composable(TaskRoute.AddEdit.route) {
            AddEditTaskScreen( onDone = {
                navController.popBackStack()
            }, onBack = {
                navController.popBackStack()
            })
        }

       composable(TaskRoute.Edit.route,
           arguments = listOf(navArgument("taskId"){
           type = NavType.LongType
       })) { backStackEntry->
           val taskId = backStackEntry.arguments?.getLong("taskId")?:0L
           AddEditTaskScreen(
               taskId = taskId   ,
               onDone = {
               navController.popBackStack()
           },  onBack = {
                   navController.popBackStack()
               })
       }
    }

}