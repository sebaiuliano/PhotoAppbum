package com.siuliano.photoappbum.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView
import com.siuliano.photoappbum.databinding.ItemPhotoBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Photo
import com.squareup.picasso.Picasso

class PhotoViewHolder(
    private val binding: ItemPhotoBinding,
    private val listener: CustomClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        Picasso.get().load(photo.thumbnailUrl)
            .into(binding.ivPhoto)
        binding.tvPhotoDescription.text = photo.title
        itemView.setOnClickListener {
            listener.onItemClick(photo, adapterPosition)
        }
    }
}