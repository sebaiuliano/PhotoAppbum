package com.siuliano.photoappbum.ui.main.callback

import androidx.recyclerview.widget.ListUpdateCallback
import com.siuliano.photoappbum.ui.main.adapters.AlbumAdapter

object AlbumCallback : ListUpdateCallback {
    var firstInsert = -1
    private lateinit var adapter : AlbumAdapter

    fun bind(adapter: AlbumAdapter) {
        this.adapter = adapter
    }

    override fun onInserted(position: Int, count: Int) {
        if (firstInsert == -1 || firstInsert > position) {
            firstInsert = position
        }
        adapter.notifyItemRangeInserted(position, count)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.notifyItemRangeRemoved(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter.notifyItemRangeChanged(position, count, payload)
    }
}