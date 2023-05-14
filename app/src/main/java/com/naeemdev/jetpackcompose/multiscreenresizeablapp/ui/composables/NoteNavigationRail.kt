package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.R
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.Screen

@Composable
@Preview
fun NoteNavigationRail(
  onDrawerClicked: () -> Unit = {},
  selectedDestination: MutableState<String> = rememberSaveable { mutableStateOf(Screen.Notes.route) },
  navController: NavController = rememberNavController()
) {
  NavigationRail(modifier = Modifier.fillMaxHeight()) {
    NavigationRailItem(
      selected = false,
      onClick = onDrawerClicked,
      icon = {
        Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = stringResource(id = R.string.app_name)
        )
      }
    )
    NavigationRailItem(
      selected = selectedDestination.value == Screen.Notes.route,
      onClick = {
        selectedDestination.value = Screen.Notes.route
        navController.navigate(Screen.Notes.route){
          popUpTo(Screen.Notes.route) { inclusive = true }
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Home,
          contentDescription = stringResource(id = R.string.noteList)
        )
      }
    )
    NavigationRailItem(
      selected = selectedDestination.value == Screen.Profile.route,
      onClick = {
        selectedDestination.value = Screen.Profile.route
        navController.navigate(Screen.Profile.route){
          popUpTo(Screen.Notes.route)
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Person,
          stringResource(id = R.string.profile)
        )
      }
    )

    NavigationRailItem(
      selected = selectedDestination.value == Screen.DeletedNotes.route,
      onClick = {
        selectedDestination.value = Screen.DeletedNotes.route
        navController.navigate(Screen.DeletedNotes.route){
          popUpTo(Screen.Notes.route)
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Delete,
          stringResource(id = R.string.deleted)
        )
      }
    )
  }
}

