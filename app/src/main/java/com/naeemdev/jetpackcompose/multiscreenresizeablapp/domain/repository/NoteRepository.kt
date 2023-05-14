package com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.repository

import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
  fun getNotes(): Flow<List<Note>>
  fun getDeletedNotes(): Flow<List<Note>>
}
