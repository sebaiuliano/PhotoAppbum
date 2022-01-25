package com.siuliano.photoappbum.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.siuliano.photoappbum.R
import com.siuliano.photoappbum.databinding.FragmentPhotoListBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Photo
import com.siuliano.photoappbum.ui.main.MainViewModel
import com.siuliano.photoappbum.ui.main.adapters.PhotoAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PhotoListFragment : Fragment() {
    private lateinit var binding: FragmentPhotoListBinding
    private val model: MainViewModel by sharedViewModel()
    private val photoAdapter = PhotoAdapter(object: CustomClickListener {
        override fun onItemClick(item: Any, position: Int) {
            if (item is Photo) {
                model.selectedPhoto = item
                goDetailDialogFragment()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_list, container, false)
        initializePhotos()
        return binding.root
    }

    private fun initializePhotos() {
        binding.rvPhotos.adapter = photoAdapter
        model.selectedAlbum?.let {
            photoAdapter.setPhotos(it.photos)
        }
    }

    private fun goDetailDialogFragment() {
        PhotoDetailDialogFragment().show(childFragmentManager, "photo_detail")
    }
}