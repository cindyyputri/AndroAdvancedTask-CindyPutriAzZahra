package com.example.taskadvanced.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.taskadvanced.R
import com.example.taskadvanced.database.DatabaseNote
import com.example.taskadvanced.databinding.ActivityDetailNotesBinding
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.repositories.NoteRepository
import com.example.taskadvanced.utils.ViewModelFactory
import com.example.taskadvanced.viewmodels.NoteViewModel
import com.example.taskadvanced.view.NotesFragment

class DetailNotesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID_NOTE")
        getData(noteViewModel(),getId!!.toInt())
        binding.btnAdd.setOnClickListener {
            addData(noteViewModel(),getId!!.toInt())
        }

    }

    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun getData(viewModel: NoteViewModel,getId:Int) {

        viewModel.getNoteId(getId).observe(this) {
            binding.etName.setText(it.name)
            binding.etOverview.setText(it.overview)
        }
    }

    fun addData(vm:NoteViewModel,id:Int) {
        vm.updateData(
            NoteEntity(
                id = id,
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