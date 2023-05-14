package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.model.Note
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.NotesViewModel

@Composable
fun NoteDetailScreen(notesViewModel: NotesViewModel = viewModel(), noteIndex: Int = 0){
  val note: Note = notesViewModel.notes.collectAsState().value[noteIndex]
  NoteDetailComposable(note = note)
}
