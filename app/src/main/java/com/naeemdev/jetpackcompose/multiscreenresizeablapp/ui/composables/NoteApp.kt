package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.theme.MultiScreenResizeablAppTheme
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.ContentType
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.DevicePosture
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.NavigationType

@Composable
fun NoteApp(
  windowSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
  devicePosture: DevicePosture = DevicePosture.NormalPosture,
  notesViewModel: NotesViewModel = viewModel()
) {
  val navController = rememberNavController()
  val contentType: ContentType
  val navigationType: NavigationType
  when (windowSizeClass) {
    WindowWidthSizeClass.Compact -> {
      navigationType = NavigationType.BOTTOM_NAVIGATION
      contentType = ContentType.LIST_ONLY
    }
    WindowWidthSizeClass.Medium -> {
      navigationType = NavigationType.NAVIGATION_RAIL
      contentType = if (devicePosture is DevicePosture.BookPosture
        || devicePosture is DevicePosture.Separating
      ) {
        ContentType.LIST_AND_DETAIL
      } else {
        ContentType.LIST_ONLY
      }
    }
    WindowWidthSizeClass.Expanded -> {
      navigationType = if (devicePosture is DevicePosture.BookPosture) {
        NavigationType.NAVIGATION_RAIL
      } else {
        NavigationType.PERMANENT_NAVIGATION_DRAWER
      }
      contentType = ContentType.LIST_AND_DETAIL
    }
    else -> {
      navigationType = NavigationType.BOTTOM_NAVIGATION
      contentType = ContentType.LIST_ONLY
    }
  }

  NoteNavigationWrapperUi(
    contentType = contentType,
    navigationType = navigationType,
    navController = navController,
    notesViewModel = notesViewModel,
  )
}


@Preview(showBackground = true)
@Composable
fun NotePreview() {
  MultiScreenResizeablAppTheme {
    NoteApp(
      windowSizeClass = WindowWidthSizeClass.Compact,
      devicePosture = DevicePosture.NormalPosture
    )
  }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun NotePreviewTablet() {
  MultiScreenResizeablAppTheme {
    NoteApp(
      devicePosture = DevicePosture.NormalPosture
    )
  }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun NotePreviewDesktop() {
  MultiScreenResizeablAppTheme {
    NoteApp(
      windowSizeClass = WindowWidthSizeClass.Expanded,
      devicePosture = DevicePosture.NormalPosture
    )
  }
}










