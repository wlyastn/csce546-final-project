package com.example.noteapp

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    val notes: Flow<List<Note>> = dao.getAllNotes()

    suspend fun addNote(note: Note) = dao.insert(note)
    suspend fun deleteNote(note: Note) = dao.delete(note)
}
