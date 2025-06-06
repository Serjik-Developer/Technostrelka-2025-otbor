package com.example.texnostrelka_2025_otbor.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.texnostrelka_2025_otbor.R
import com.example.texnostrelka_2025_otbor.presentation.listener.OnItemClickListener
import com.example.texnostrelka_2025_otbor.data.model.ComicsModel
import com.google.android.material.button.MaterialButton

class ComicsAdapter(private var comics: MutableList<ComicsModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ComicsAdapter.ComiksViewHolder>() {

    inner class ComiksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.komiks_name)
        val textViewDesc: TextView = itemView.findViewById(R.id.komiks_description)
        val delete_btn: MaterialButton = itemView.findViewById(R.id.delete_comics_btn)
        val edit_btn: MaterialButton = itemView.findViewById(R.id.edit_comics_btn)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val uploadButton: MaterialButton = itemView.findViewById(R.id.upload_comics_btn)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val id = comics[position].id!!
                    listener.onItemClick(id)
                }
            }
            delete_btn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val id = comics[position].id!!
                    listener.onDeleteClick(id)
                }
            }
            edit_btn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val id = comics[position].id!!
                    listener.onEditClick(id)
                }
            }
            uploadButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onSendClick(comics[position].id!!)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newComics: MutableList<ComicsModel>) {
        comics = newComics
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComiksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.komiks_rvview, parent, false)
        return ComiksViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComiksViewHolder, position: Int) {
        val komiksItem = comics[position]
        holder.textView.text = komiksItem.text
        holder.textViewDesc.text = komiksItem.description
        if (komiksItem.image != null) {
            holder.imageView.setImageBitmap(komiksItem.image)
        }

    }

    override fun getItemCount(): Int = comics.size
}