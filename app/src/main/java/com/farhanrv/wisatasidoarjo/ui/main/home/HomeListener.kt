package com.farhanrv.wisatasidoarjo.ui.main.home

import android.widget.ImageView
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity

interface HomeListener {
    fun onItemClick(wisata: WisataListEntity, imageView: ImageView)
}