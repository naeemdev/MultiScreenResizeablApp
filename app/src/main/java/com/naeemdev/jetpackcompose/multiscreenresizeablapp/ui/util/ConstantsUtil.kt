package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util

sealed class Screen(val route: String) {
  object Notes : Screen("notes")
  object NoteDetail : Screen("notes/{noteIndex}"){
    fun createRoute(noteIndex: Int) = "notes/$noteIndex"
  }
  object DeletedNotes: Screen("notes/deleted")
  object Profile: Screen("profile")
}

/**
 * Different type of navigation depending on size and state.
 */
enum class NavigationType {
  BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Content shown depending on size and state of device.
 */
enum class ContentType {
  LIST_ONLY, LIST_AND_DETAIL
}
