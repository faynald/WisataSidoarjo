package com.farhanrv.wisatasidoarjo.ui.main.home

import android.widget.ImageView
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity

interface HomeCallback {
    fun toDetailActivity(wisata: WisataListEntity, imageView: ImageView)
    fun hideSearch()
    fun showSearch()
}