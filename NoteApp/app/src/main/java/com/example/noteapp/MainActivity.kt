package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.ui.navigation.AppNavigation
import com.example.noteapp.ui.theme.NoteAppTheme // Make sure this is the correct theme name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NoteViewModel = viewModel()
            NoteAppTheme {
                AppNavigation(viewModel)
            }
        }
    }
}
