package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.ContentType
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.NavigationType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteNavigationWrapperUi(
  modifier: Modifier = Modifier,
  contentType: ContentType = ContentType.LIST_ONLY,
  navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
  navController: NavHostController = rememberNavController(),
  notesViewModel: NotesViewModel = viewModel()
) {
  val scope = rememberCoroutineScope()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
    PermanentNavigationDrawer(drawerContent = {
      NavigationDrawerContent(
        navController = navController
      )
    }) {
      NoteAppContent(
        navigationType = navigationType,
        contentType = contentType,
        modifier = modifier,
        navController = navController,
        notesViewModel = notesViewModel
      )
    }
  } else {
    ModalNavigationDrawer(
      drawerContent = {
        NavigationDrawerContent(
          navController = navController,
          onDrawerClicked = {
            scope.launch {
              drawerState.close()
            }
          }
        )
      },
      drawerState = drawerState
    ) {
      NoteAppContent(
        navigationType = navigationType,
        contentType = contentType,
        modifier = modifier,
        navController = navController,
        notesViewModel = notesViewModel,
        onDrawerClicked = {
          scope.launch {
            drawerState.open()
          }
        }
      )
    }
  }
}

