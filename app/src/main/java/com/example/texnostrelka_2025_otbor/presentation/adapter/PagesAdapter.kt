package com.example.texnostrelka_2025_otbor.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.texnostrelka_2025_otbor.R
import com.example.texnostrelka_2025_otbor.presentation.listener.OnItemPageClickListener
import com.example.texnostrelka_2025_otbor.data.model.PageModel
import com.example.texnostrelka_2025_otbor.data.model.PageWithImagesModel
import com.example.texnostrelka_2025_otbor.domain.repository.ComicsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PagesAdapter(
    private val context: Context,
    private var items: MutableList<PageModel>,
    private val listener: OnItemPageClickListener,
    private val comicsRepository: ComicsRepository,
    private val coroutineScope: CoroutineScope
) : RecyclerView.Adapter<PagesAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pages_item, parent, false)
        return PageViewHolder(context, view, listener, comicsRepository, coroutineScope)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(items[position])
    }
    fun updateData(newItems: MutableList<PageModel>) {
        items = newItems
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    override fun getItemCount(): Int = items.size

    class PageViewHolder(
        private val context: Context,
        itemView: View,
        private val listener: OnItemPageClickListener,
        private val comicsRepository: ComicsRepository,
        private val coroutineScope: CoroutineScope
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageRecyclerView: RecyclerView = itemView.findViewById(R.id.imageRecyclerView)
        private val editBtn: ImageButton = itemView.findViewById(R.id.edit_page_btn)
        private val deleteBtn: ImageButton = itemView.findViewById(R.id.delete_page_btn)

        fun bind(item: PageModel) {
            coroutineScope.launch {
                val imageList = comicsRepository.getAllImagesOnPage(item.pageId)

                withContext(Dispatchers.Main) {
                    imageRecyclerView.layoutManager = GridLayoutManager(context, item.columns)
                    imageRecyclerView.adapter = ImageAdapter(imageList)
                    deleteBtn.setOnClickListener {
                        listener.onDeleteClick(item.pageId)
                    }
                    editBtn.setOnClickListener {
                        listener.onEditClick(PageWithImagesModel(item, imageList))
                    }
                }
            }
        }
    }
}