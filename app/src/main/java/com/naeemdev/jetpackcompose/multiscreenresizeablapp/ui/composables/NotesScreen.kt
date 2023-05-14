package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.ContentType

@Composable
fun NotesScreen(
  contentType: ContentType,
  onNoteItemSelected: (index: Int) -> Unit,
  notesViewModel: NotesViewModel = viewModel(),
  getNotes: () -> Unit = { notesViewModel.getNotes() },
) {
  val onGetNotes by rememberUpdatedState(newValue = getNotes)
  LaunchedEffect(true) {
    onGetNotes()
  }
  val notes = notesViewModel.notes.collectAsState().value
  if (contentType == ContentType.LIST_AND_DETAIL) {
    NoteListDetailComposable(notes = notes)
  } else {
    NotesListComposable(
      notes = notes,
      onItemSelected = onNoteItemSelected,
    )
  }
}
