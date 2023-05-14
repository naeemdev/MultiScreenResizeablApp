package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.ContentType
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.Screen

@Composable
fun NoteNavHost(
  contentType: ContentType = ContentType.LIST_ONLY,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  notesViewModel: NotesViewModel = viewModel(),
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Notes.route,
    modifier = modifier
  ) {
    composable(Screen.Notes.route) {
      NotesScreen(
        notesViewModel = notesViewModel,
        contentType = contentType,
        onNoteItemSelected = {index ->
          val route = Screen.NoteDetail.createRoute(index)
          navController.navigate(route)
        }
      )
    }
    composable(Screen.NoteDetail.route) { backStackEntry ->
      val noteIndex =
        backStackEntry.arguments?.getString("noteIndex")?.toInt() ?: 0
      NoteDetailScreen(
        notesViewModel = notesViewModel,
        noteIndex = noteIndex
      )
    }
    composable(Screen.Profile.route) {
      ProfileScreen()
    }
    composable(Screen.DeletedNotes.route) {
      DeletedNotesScreen(notesViewModel = notesViewModel, modifier = modifier.padding(32.dp))
    }
  }
}
