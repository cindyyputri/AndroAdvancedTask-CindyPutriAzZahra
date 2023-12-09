package com.example.taskadvanced.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskadvanced.entitas.NoteEntity
import com.example.taskadvanced.databinding.ItemNotesBinding

class NotesRvAdapter (private val list: List<NoteEntity>, val onDelete:(NoteEntity) -> Unit, val onClick:(Int) -> Unit) :
    RecyclerView.Adapter<NotesRvAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder) {
                with(list[position]) {
                    binding.tvName.text = this.name
                    binding.tvOverview.text = this.overview
                    binding.cdCard.setOnClickListener {
                        onClick.invoke(this.id)
                    }
                    binding.ivTrash.setOnClickListener {
                        onDelete.invoke(this)
                    }
                }
            }
        }

        override fun getItemCount(): Int = list.size
    }