package com.appwest.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleET : EditText
    lateinit var noteDescriptionET : EditText
    lateinit var addUpdateBtn : Button
    lateinit var viewModel : NoteViewModel
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        noteTitleET = findViewById(R.id.idETNoteName)
        noteDescriptionET = findViewById(R.id.idETNoteDescription)
        addUpdateBtn = findViewById(R.id.idBtnAddUpdate)

        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("note-Type")

        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("note-Title")
            val noteDescription = intent.getStringExtra("note-Description")
            noteID = intent.getIntExtra("note-ID",-1)

            addUpdateBtn.setText("Update Note")
            noteTitleET.setText(noteTitle)
            noteDescriptionET.setText(noteDescription)
        }else{   // Add
            addUpdateBtn.setText("Save Note")
        }

        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleET.text.toString()
            val noteDescription = noteDescriptionET.text.toString()

            if (noteType.equals("Edit")) {
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime :String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            }else{  // Add
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val addNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    viewModel.addNote(addNote)
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext , MainActivity::class.java))
            this.finish()
        }
    }
}