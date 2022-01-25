package com.siuliano.photoappbum.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.siuliano.photoappbum.databinding.ItemAlbumBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Album
import com.siuliano.photoappbum.ui.main.callback.AlbumCallback

class AlbumAdapter(
    private val listener: CustomClickListener
) : RecyclerView.Adapter<AlbumViewHolder>() {
    private var albumList : List<Album> = ArrayList()

    init {
        AlbumCallback.bind(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(inflater, parent,false)
        return AlbumViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    fun setAlbums(newList: List<Album>) {
        val oldList = albumList
        albumList = newList
        notifyChanges(oldList, albumList)
    }

    private fun notifyChanges(oldList: List<Album>, newList: List<Album>) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].albumId == newList[newItemPosition].albumId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        }, false)

        diff.dispatchUpdatesTo(AlbumCallback)
    }
}