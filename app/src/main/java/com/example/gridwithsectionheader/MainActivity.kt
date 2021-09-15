package com.example.gridwithsectionheader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gridwithsectionheader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyRecyclerAdapter()
        adapter.list = getData()
        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemViewType(position) == adapter.VIEW_TYPE_HEADER) 4 else 1
            }
        }
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter
    }

    data class Item(val text: String, val type: String)
    private fun getData(): ArrayList<Item> {
        val list  = ArrayList<Item>()
        for (i in 0..100) {
            if (i % 7 == 0) {
                list.add(Item("Header " + (i / 7 + 1), "header"))
            }
            list.add( Item("Item " + ( i + 1), "item"))
        }
        return list
    }
}