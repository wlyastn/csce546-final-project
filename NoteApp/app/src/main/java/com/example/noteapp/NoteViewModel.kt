package com.example.noteapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.asLiveData()
    }

    fun addNote(title: String, text: String, imageUri: String? = null) = viewModelScope.launch {
        repo.addNote(Note(title = title, text = text, imageUri = imageUri))
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }
}