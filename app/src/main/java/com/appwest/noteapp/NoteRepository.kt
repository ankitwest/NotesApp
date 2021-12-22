package com.appwest.noteapp

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao:NotesDao) {
//    on below line we are creating a variable for our list and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

//    we are creating an insert method
    suspend fun insert(note:Note){
        notesDao.insert(note)
    }
    suspend fun delete(note:Note){
        notesDao.delete(note)
    }
    suspend fun update(note:Note){
        notesDao.update(note)
    }
}