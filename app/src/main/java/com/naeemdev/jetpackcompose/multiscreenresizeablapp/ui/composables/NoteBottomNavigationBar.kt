package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.R
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.Screen

@Composable
fun NoteBottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
  NavigationBar(
    modifier = modifier.fillMaxWidth(),
    containerColor = MaterialTheme.colorScheme.background
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBarItem(
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Notes.route } == true,
      onClick = {
        navController.navigate(Screen.Notes.route) {
          popUpTo(Screen.Notes.route){
            inclusive = true
          }
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Home,
          contentDescription = stringResource(id = R.string.noteList)
        )
      }
    )

    NavigationBarItem(
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Profile.route } == true,
      onClick = {
        navController.navigate(Screen.Profile.route) {
          popUpTo(Screen.Notes.route)
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Outlined.Person,
          contentDescription = stringResource(id = R.string.profile)
        )
      }
    )
    NavigationBarItem(
      selected = currentDestination?.hierarchy?.any { it.route == Screen.DeletedNotes.route } == true,
      onClick = {
        navController.navigate(Screen.DeletedNotes.route) {
          popUpTo(Screen.Notes.route)
        }
      },
      icon = {
        Icon(
          imageVector = Icons.Outlined.Delete,
          contentDescription = stringResource(id = R.string.deleted)
        )
      }
    )
  }
}
