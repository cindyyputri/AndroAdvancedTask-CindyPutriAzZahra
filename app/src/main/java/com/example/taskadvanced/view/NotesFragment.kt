package com.example.taskadvanced.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskadvanced.R
import com.example.taskadvanced.adapters.NotesRvAdapter
import com.example.taskadvanced.database.DatabaseNote
import com.example.taskadvanced.databinding.ActivityMainBinding
import com.example.taskadvanced.databinding.FragmentNotesBinding
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.repositories.NoteRepository
import com.example.taskadvanced.utils.ViewModelFactory
import com.example.taskadvanced.viewmodels.NoteViewModel

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NotesRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = noteViewModel()
        init(viewModel)
        onClick()
    }

    private fun init(vm: NoteViewModel) {
        changeData(vm) { list ->
            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvList.layoutManager = layoutManager
            adapter = NotesRvAdapter(list, onDelete = { item ->
                vm.deleteData(item).let {
                    Toast.makeText(requireContext(), "Udah kehapus", Toast.LENGTH_SHORT).show()
                }
            }) {
                val intent = Intent(requireContext(), DetailNotesActivity::class.java)
                intent.putExtra("ID_NOTE", it.toString())
                startActivity(intent)
            }
            binding.rvList.adapter = adapter
        }
    }

    private fun onClick() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddNotesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(requireContext())
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    private fun changeData(vm: NoteViewModel, getData: (List<NoteEntity>) -> Unit) {
        vm.getAllNoteData().observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                getData.invoke(list)
                binding.rvList.visibility = View.VISIBLE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.rvList.visibility = View.GONE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }

}