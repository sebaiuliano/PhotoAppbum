package com.siuliano.photoappbum.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView
import com.siuliano.photoappbum.R
import com.siuliano.photoappbum.databinding.ItemAlbumBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Album
import com.squareup.picasso.Picasso

class AlbumViewHolder(
    private val binding: ItemAlbumBinding,
    private val listener: CustomClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(album: Album) {
        binding.tvAlbumTitle.text = itemView.context.getString(R.string.album_name, album.albumId)
        Picasso.get().load(album.photos.first { it.thumbnailUrl.isNotEmpty() }.thumbnailUrl)
            .into(binding.ivAlbum)
        itemView.setOnClickListener { listener.onItemClick(album, adapterPosition) }
    }
}