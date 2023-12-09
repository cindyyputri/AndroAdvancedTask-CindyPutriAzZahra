package com.example.taskadvanced.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.taskadvanced.R
import com.example.taskadvanced.database.DatabaseNote
import com.example.taskadvanced.databinding.ActivityAddNotesBinding
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.repositories.NoteRepository
import com.example.taskadvanced.utils.ViewModelFactory
import com.example.taskadvanced.viewmodels.NoteViewModel

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addData(noteViewModel())
        }
    }

    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun addData(vm:NoteViewModel) {
        vm.insertData(
            NoteEntity(
            name = binding.etName.text.toString(),
            overview = binding.etOverview.text.toString()
        )
        ).let {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}