package com.naeemdev.jetpackcompose.multiscreenresizeablapp.data.repository

import com.naeemdev.jetpackcompose.multiscreenresizeablapp.data.datasource.NotesDataSource
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow

class NoteRepositoryImpl : NoteRepository {
  override fun getNotes() = flow {
    val notes = NotesDataSource.notes
    emit(notes)
  }

  override fun getDeletedNotes() = flow{
    val deletedNotes = NotesDataSource.deletedNotes
    emit(deletedNotes)
  }
}
