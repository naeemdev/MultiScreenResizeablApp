package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.R
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
  navController: NavController,
  modifier: Modifier = Modifier,
  selectedDestination: MutableState<String> = rememberSaveable { mutableStateOf(Screen.Notes.route) },
  onDrawerClicked: () -> Unit = {}
) {
  Column(
    modifier
      .wrapContentWidth()
      .fillMaxHeight()
      .background(MaterialTheme.colorScheme.inverseOnSurface)
      .padding(24.dp)
  ) {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = stringResource(id = R.string.app_name).uppercase(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
      )
      IconButton(onClick = onDrawerClicked) {
        Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = stringResource(id = R.string.app_name)
        )
      }
    }

    NavigationDrawerItem(
      selected = selectedDestination.value == Screen.Notes.route,
      label = {
        Text(
          text = stringResource(id = R.string.noteList),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Home,
          contentDescription = stringResource(id = R.string.noteList)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = {
        selectedDestination.value = Screen.Notes.route
        navController.navigate(Screen.Notes.route) {
          popUpTo(Screen.Notes.route) { inclusive = true }
        }
      }
    )
    NavigationDrawerItem(
      selected = selectedDestination.value == Screen.Profile.route,
      label = {
        Text(
          text = stringResource(id = R.string.profile),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Person,
          contentDescription = stringResource(id = R.string.profile)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = {
        selectedDestination.value = Screen.Profile.route
        navController.navigate(Screen.Profile.route) {
          popUpTo(Screen.Notes.route)
        }
      }
    )
    NavigationDrawerItem(
      selected = selectedDestination.value == Screen.DeletedNotes.route,
      label = {
        Text(
          text = stringResource(id = R.string.deleted),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Delete,
          contentDescription = stringResource(id = R.string.deleted)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = {
        selectedDestination.value = Screen.DeletedNotes.route
        navController.navigate(Screen.DeletedNotes.route) {
          popUpTo(Screen.Notes.route)
        }
      }
    )
  }
}
