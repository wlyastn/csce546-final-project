package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.noteapp.NoteViewModel
import com.example.noteapp.ui.NoteScreen
import com.example.noteapp.ui.EditNoteScreen
import com.example.noteapp.ui.SavedNotesScreen

@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "create") {
        composable("create") {
            NoteScreen(viewModel, onNavigateToSaved = {
                navController.navigate("saved")
            })
        }
        composable("saved") {
            SavedNotesScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onEditNote = { note ->
                    navController.navigate("edit/${note.id}")
                }
            )
        }
        composable(
            route = "edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val note = viewModel.notes.value?.find { it.id == noteId }

            note?.let {
                EditNoteScreen(
                    note = it,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
