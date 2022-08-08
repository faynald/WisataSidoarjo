package com.farhanrv.wisatasidoarjo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.wisatasidoarjo.data.DataRepository

class DetailViewModel(private val repository: DataRepository) : ViewModel() {

    fun getDetail(kode: String) =
        repository.getDetailWisata(kode).asLiveData()

    fun setFavorite(kode: String, newStatus: Boolean) =
        repository.setFavorite(kode, newStatus)
}