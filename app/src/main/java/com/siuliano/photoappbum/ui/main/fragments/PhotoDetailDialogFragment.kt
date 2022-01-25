package com.siuliano.photoappbum.ui.main.fragments

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.siuliano.photoappbum.R
import com.siuliano.photoappbum.databinding.DialogFragmentPhotoDetailBinding
import com.siuliano.photoappbum.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PhotoDetailDialogFragment : DialogFragment() {
    private lateinit var binding: DialogFragmentPhotoDetailBinding
    private val model: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_photo_detail, container, false)
        setPhoto()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setViewMetric()
    }

    private fun setPhoto() {
        model.selectedPhoto?.let {
            binding.tvPhotoDescription.text = it.title
            Picasso.get().load(it.url)
                .into(binding.ivPhoto)
        }
    }

    //Configuro el tamaño del dialog segun dimensiones de pantalla
    private fun setViewMetric(){
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        val displayWidth = displayMetrics.widthPixels
        val displayHeight = displayMetrics.heightPixels
        val dialogWindowWidth = (displayWidth *  if (displayHeight > displayWidth) { 0.7f } else { 0.5f }).toInt()
        val dialogWindowHeight = (displayHeight * if (displayHeight > displayWidth) { 0.6f } else { 0.9f }).toInt()
        val layoutParams = WindowManager.LayoutParams()

        binding.clPhotoDetail.minimumHeight = dialogWindowHeight
        binding.clPhotoDetail.minimumWidth = dialogWindowWidth

        layoutParams.copyFrom(dialog?.window?.attributes)
        layoutParams.width = dialogWindowWidth
        layoutParams.height = dialogWindowHeight

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes = layoutParams
    }
}