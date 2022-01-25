package com.siuliano.photoappbum.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.siuliano.photoappbum.R
import com.siuliano.photoappbum.databinding.ActivityMainBinding
import com.siuliano.photoappbum.interfaces.CustomClickListener
import com.siuliano.photoappbum.models.Album
import com.siuliano.photoappbum.ui.main.adapters.AlbumAdapter
import com.siuliano.photoappbum.ui.main.fragments.PhotoListFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModel()
    private lateinit var binding : ActivityMainBinding
    private val albumAdapter = AlbumAdapter(object: CustomClickListener {
        override fun onItemClick(item: Any, position: Int) {
            if (item is Album) {
                model.selectedAlbum = item
                goPhotoListFragment()
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeAlbumsRv()
        setObservables()
        initializeAlbums()
    }

    private fun setObservables() {
        model.photosMutableHandler.observe(this) {
            if (it.isNotEmpty()) {
                albumAdapter.setAlbums(it)
            }
        }
    }

    private fun initializeAlbumsRv() {
        binding.rvAlbums.adapter = albumAdapter
    }

    private fun initializeAlbums() {
        model.getPhotos()
    }

    private fun goPhotoListFragment() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.cl_main, PhotoListFragment(), "photo_list")
            .commit()
    }
}