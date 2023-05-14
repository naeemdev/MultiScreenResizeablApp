package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.model.Note
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.repository.NoteRepository
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.data.repository.NoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
  private val repository: NoteRepository = NoteRepositoryImpl()
) : ViewModel() {

  private val _notes = MutableStateFlow<List<Note>>(emptyList())
  val notes: StateFlow<List<Note>>
    get() = _notes

  private val _deletedNotes = MutableStateFlow<List<Note>>(emptyList())
  val deletedNotes: StateFlow<List<Note>>
    get() = _deletedNotes

  fun getNotes() {
    viewModelScope.launch {
      val result = repository.getNotes()
      result.collect { notesList ->
        _notes.value = notesList
      }
    }
  }

  fun getDeletedNotes() {
    viewModelScope.launch {
      val result = repository.getDeletedNotes()
      result.collect { notesList ->
        _deletedNotes.value = notesList
      }
    }
  }
}
