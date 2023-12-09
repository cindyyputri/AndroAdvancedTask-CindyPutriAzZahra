package com.example.taskadvanced.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.taskadvanced.repositories.NoteRepository
import com.example.taskadvanced.viewmodels.NoteViewModel

class ViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(NoteRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {

        }
        return super.create(modelClass)
    }
}