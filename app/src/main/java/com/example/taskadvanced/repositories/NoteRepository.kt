package com.example.taskadvanced.repositories

import androidx.lifecycle.LiveData
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.database.DatabaseNote

class NoteRepository(
    val database:DatabaseNote
) {
    fun getAllNoteData(): LiveData<List<NoteEntity>> = database.dao().getAllNoteData()
    fun getNoteId(id:Int): LiveData<NoteEntity> = database.dao().getNoteId(id)
    suspend fun insertData(data:NoteEntity) = database.dao().insertData(data)
    suspend fun updateData(data:NoteEntity) = database.dao().updateData(data)
    suspend fun deleteData(id:NoteEntity) = database.dao().deleteData(id)
}