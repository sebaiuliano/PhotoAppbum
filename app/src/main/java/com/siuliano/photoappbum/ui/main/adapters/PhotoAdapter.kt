package com.siuliano.photoappbum.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.siuliano.photoappbum.databinding.ItemPhotoBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Photo
import com.siuliano.photoappbum.ui.main.callback.PhotoCallback

class PhotoAdapter(
    private val listener : CustomClickListener
) : RecyclerView.Adapter<PhotoViewHolder>() {
    private var photoList : List<Photo> = ArrayList()

    init {
        PhotoCallback.bind(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent,false)
        return PhotoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setPhotos(newList: List<Photo>) {
        val oldList = photoList
        photoList = newList
        notifyChanges(oldList, photoList)
    }

    private fun notifyChanges(oldList: List<Photo>, newList: List<Photo>) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        }, false)

        diff.dispatchUpdatesTo(PhotoCallback)
    }
}