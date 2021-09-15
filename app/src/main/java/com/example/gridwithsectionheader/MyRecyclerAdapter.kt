package com.example.gridwithsectionheader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gridwithsectionheader.databinding.ListItemBinding
import com.example.gridwithsectionheader.databinding.ListSectionHeaderBinding

class MyRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: ArrayList<MainActivity.Item> = ArrayList()
    val VIEW_TYPE_HEADER = 0
    val VIEW_TYPE_ITEM = 1

    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
    inner class SectionHeaderViewHolder(val binding: ListSectionHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = ListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(binding)
        } else  {
            val binding = ListSectionHeaderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            SectionHeaderViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_TYPE_ITEM -> {
                val h = holder as ItemViewHolder
                h.binding.text.text = list[position].text
            }

            VIEW_TYPE_HEADER -> {
                val h = holder as SectionHeaderViewHolder
                h.binding.headerTitle.text = list[position].text
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].type == "header") {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}