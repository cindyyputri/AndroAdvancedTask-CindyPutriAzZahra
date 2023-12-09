package com.example.taskadvanced.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.repositories.NoteRepository

class NoteViewModel(
    val repo: NoteRepository
) : ViewModel() {
    fun getAllNoteData() =
        repo.getAllNoteData()

    fun insertData(data: NoteEntity) =
        viewModelScope.launch {
            repo.insertData(data)
        }

    fun updateData(data: NoteEntity) =
        viewModelScope.launch {
            repo.updateData(data)
        }

    fun deleteData(id:NoteEntity) =
        viewModelScope.launch {
            repo.deleteData(id)
        }

    fun getNoteId(id:Int) =
        repo.getNoteId(id)

//    init {
//        insertData(data = NoteEntity(name = "Kulaih atau kerja", number = "08129392"))
//    }
}