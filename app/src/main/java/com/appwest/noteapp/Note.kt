package com.appwest.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note (
//    describing our column info
    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String
    ) {

//    specifying our key and auto generate true and initial value as 0
    @PrimaryKey(autoGenerate = true) var id = 0
}


