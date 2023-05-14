package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.ContentType
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.NavigationType

@Composable
fun NoteAppContent(
  navigationType: NavigationType,
  contentType: ContentType,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  notesViewModel: NotesViewModel = viewModel(),
  onDrawerClicked: () -> Unit = {}
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
  ) {
    Row(modifier = Modifier.fillMaxSize()) {
      AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
        NoteNavigationRail(
          onDrawerClicked = onDrawerClicked,
          navController = navController
        )
      }
      Column(
        modifier = modifier.fillMaxSize()
      ) {
        NoteNavHost(
          modifier = modifier.weight(1f),
          contentType = contentType,
          navController = navController,
          notesViewModel = notesViewModel
        )
        AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
          NoteBottomNavigationBar(navController = navController)
        }
      }
    }
  }
}
